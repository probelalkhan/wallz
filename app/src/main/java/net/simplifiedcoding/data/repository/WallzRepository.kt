package net.simplifiedcoding.data.repository

import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.network.SafeApiCall
import net.simplifiedcoding.data.network.WallzApi
import net.simplifiedcoding.data.responses.RandomPhotosResponseItem
import net.simplifiedcoding.utils.DEFAULT_PHOTO_COUNT
import javax.inject.Inject


class WallzRepository @Inject constructor(
    private val api: WallzApi
) : SafeApiCall {

    suspend fun getRandomPhotos(): Resource<List<RandomPhotosResponseItem>> =
        safeApiCall { api.getRandomPhotos(DEFAULT_PHOTO_COUNT) }

}