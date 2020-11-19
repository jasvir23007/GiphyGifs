package com.jasvir.freshworks.api

import com.jasvir.freshworks.api.ApiConstants.SEARCH_API_ENDPOINT
import com.jasvir.freshworks.api.ApiConstants.TRENDING_API_ENDPOINT
import com.jasvir.freshworks.model.Response
import com.jasvir.freshworks.utils.Constants.Companion.APPLICATION_ID
import com.jasvir.freshworks.utils.Constants.Companion.KEYWORD
import com.jasvir.freshworks.utils.Constants.Companion.PAGE
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * interface for retrofit api call
 */
interface SearchService {

    /**
     * @param id key for giphy is passed here
     * @param query it is text by gif's will be filtered
     * @param page parameter for pagination
     */
    @GET(SEARCH_API_ENDPOINT)
    fun search( @Query(APPLICATION_ID) id:String,
        @Query(KEYWORD) query: String,
        @Query(PAGE) page: Int
    ): Deferred<Response>


    /**
     * @param id key for giphy is passed here
     * @param page parameter for pagination
     */
    @GET(TRENDING_API_ENDPOINT)
    fun trending( @Query(APPLICATION_ID) id:String,
                  @Query(PAGE) page: Int
    ): Deferred<Response>



}