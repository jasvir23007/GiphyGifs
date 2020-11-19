package com.jasvir.freshworks.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.repo.SearchRepo
import kotlinx.coroutines.CoroutineScope

/**
 * Factory class for pagination as per paging library
 *
 * @property repository object of repository class to get data
 * @property query text to be searched
 * @property scope coroutine scope
 */
class SearchDataSourceFactory(
    private val repository: SearchRepo,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, SearchDB>() {

    val source = MutableLiveData<SearchDataSource>()

    override fun create(): DataSource<Int, SearchDB> {
        val source = SearchDataSource(repository, query, scope)
        this.source.postValue(source)
        return source
    }


    fun getSource() = source.value

    fun updateQuery(query: String) {
        this.query = query
        getSource()?.refresh()
    }

    /**
     * function to save data in DB
     *
     * @param search param to be added inDB
     */
    fun savePersistence(search: SearchDB?) {
        getSource()?.savePersistence(search)
    }

    /**
     * function returns list present in DB
     *
     * @return
     */
    fun getAllPersistence(): List<SearchDB>? {
        return getSource()?.getAllPersistence()
    }

    /**
     * delete item from DB by id
     *
     * @param id used in room query to delete
     */
    fun deleteById(id: String) {
        getSource()?.deleteById(id)
    }


}