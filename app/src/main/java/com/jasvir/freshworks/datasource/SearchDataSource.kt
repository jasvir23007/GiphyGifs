package com.jasvir.freshworks.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.jasvir.freshworks.api.NetworkState
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.repo.SearchRepo
import kotlinx.coroutines.*

/**
 * class for pagination datasource as per paginging libaray docs
 * @param repository to get data
 * @param query parameter to search for
 * @param scope coroutine scope for various operations
 */
class SearchDataSource(
    private val repository: SearchRepo,
    private val query: String,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, SearchDB>() {

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null //Keep the last query just in case it fails

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SearchDB>
    ) {
        retryQuery = { loadInitial(params, callback) }
        executeQuery(0) {
            callback.onResult(it, null, 50)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchDB>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        executeQuery(page) {
            callback.onResult(it, (page + 50))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchDB>) {
        //Not implemented
    }


    /**
     * Function to execute pagination with callback
     *
     * @param page count of page
     * @param callback callback for result
     */
    private fun executeQuery(
        page: Int,
        callback: (List<SearchDB>) -> Unit
    ) {
        networkState.postValue(NetworkState.RUNNING)
        scope.launch(getJobErrorHandler() + supervisorJob) {
            val data = repository.searchWithPagination(query, page)
            retryQuery = null
            networkState.postValue(NetworkState.SUCCESS)
            callback(data)
        }
    }

    /**
     * handler in case there is an error
     *
     */
    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, _ ->
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    /**
     * func to get state of network
     *
     * @return network enum is returned
     */
    fun getNetworkState(): LiveData<NetworkState> =
        networkState

    fun refresh() =
        this.invalidate()

    fun retryFailedQuery() {
        val prevQuery = retryQuery
        retryQuery = null
        prevQuery?.invoke()
    }

    /**
     * function to save data to room
     *
     * @param search searched item which is to be saved in DB
     */
    fun savePersistence(search: SearchDB?) {
        scope.launch(getJobErrorHandler() + supervisorJob) {
            search?.let { repository.savePersistence(it) }
        }
    }

    /**
     * function to get all data room
     *
     * @return list of data present in DB
     */
    fun getAllPersistence(): List<SearchDB> = runBlocking {
        repository.getAllPersistence()
    }

    /**
     * delete by unique id
     *
     * @param id which id needs to be deleted
     */
    fun deleteById(id: String) {
        scope.launch(getJobErrorHandler() + supervisorJob) {
            repository.deletePersistenceById(id)
        }
    }


}