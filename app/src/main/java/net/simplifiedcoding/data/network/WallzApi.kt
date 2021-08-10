package net.simplifiedcoding.data.network

import net.simplifiedcoding.data.responses.RandomPhotosResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface WallzApi {


    @GET("photos/random")
    suspend fun getRandomPhotos(
        @Query("count") count: Int
    ): List<RandomPhotosResponseItem>

}