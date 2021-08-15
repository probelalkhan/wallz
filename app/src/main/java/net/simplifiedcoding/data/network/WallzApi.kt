package net.simplifiedcoding.data.network

import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.data.responses.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WallzApi {


    @GET("photos/random")
    suspend fun getRandomPhotos(
        @Query("count") count: Int
    ): List<PhotoResponseItem>

    @GET("search/photos")
    suspend fun getSearchResults(
        @Query("query") searchQuery: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchResponse
}