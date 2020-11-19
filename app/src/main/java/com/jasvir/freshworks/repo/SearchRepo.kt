package com.jasvir.freshworks.repo

import com.jasvir.freshworks.api.SearchService
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.persitence.SearchDB.Companion.mapList
import com.jasvir.freshworks.persitence.SearchDao
import com.jasvir.freshworks.utils.Constants.Companion.APPLICATION_ID_VALUE

/**
 * @property searchService object for api calls
 * @property dao database object to perform various operations
 */
class SearchRepo(private val searchService: SearchService, private val dao: SearchDao) {

    private suspend fun searchData(query: String, page: Int) =
        if (query.isEmpty()) searchService.trending(APPLICATION_ID_VALUE, page)
            .await() else searchService.search(APPLICATION_ID_VALUE, query, page).await()

    suspend fun searchWithPagination(query: String, page: Int): List<SearchDB> {
        val request = searchData(query, page)
        return mapList(dataList = request.items!!)
    }

    suspend fun savePersistence(search: SearchDB) {
        dao.insert(search)
    }

    suspend fun getAllPersistence(): List<SearchDB> {
        return dao.findAll()
    }

    suspend fun deletePersistence(search: SearchDB) {
        dao.delete(search)
    }

    fun deletePersistenceById(id: String) {
        dao.deleteById(id)
    }

}