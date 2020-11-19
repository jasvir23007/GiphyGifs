package com.jasvir.freshworks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import com.jasvir.freshworks.api.NetworkState
import com.jasvir.freshworks.base.BaseViewModel
import com.jasvir.freshworks.datasource.SearchDataSourceFactory
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.repo.SearchRepo
import com.jasvir.freshworks.utils.pagedListConfig

/**
 * @param repo for repository
 * shared viewmodel for both fragments
 */
class SearchViewModel(repo: SearchRepo) : BaseViewModel() {
    private val searchDataSource = SearchDataSourceFactory(repository = repo, scope = ioScope)
    val data = LivePagedListBuilder(searchDataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? =
        switchMap(searchDataSource.source) { it.getNetworkState() }
    val updateFavData = MutableLiveData<List<SearchDB>?>()

    /**
     * func to fetch data from given string
     * @param query string to get data
     */
    fun fetchByGif(query: String) {
        val search = query.trim()
        searchDataSource.updateQuery(search)
    }

    /**
     * func to add fav or remove
     * @param search to be inserted in DB or removed
     */
    fun handleFav(search: SearchDB?) {
        if (search?.isFav == 1) savePersistent(search) else deleteById(search!!.id)
        updateFavData.value = getAllPersistent()
    }

    private fun savePersistent(search: SearchDB?) {
        searchDataSource.savePersistence(search)
    }

    fun getAllPersistent(): List<SearchDB>? {
        return searchDataSource.getAllPersistence()
    }

    fun deleteById(id: String) {
        searchDataSource.deleteById(id)
    }


}