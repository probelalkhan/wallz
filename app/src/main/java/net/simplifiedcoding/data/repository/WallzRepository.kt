package net.simplifiedcoding.data.repository

import net.simplifiedcoding.data.db.OfflinePhoto
import net.simplifiedcoding.data.db.WallzDatabase
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.network.SafeApiCall
import net.simplifiedcoding.data.network.WallzApi
import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.data.responses.SearchResponse
import net.simplifiedcoding.utils.DEFAULT_PHOTO_COUNT
import javax.inject.Inject


class WallzRepository @Inject constructor(
    private val api: WallzApi,
    private val db: WallzDatabase
) : SafeApiCall {

    suspend fun getRandomPhotos(): Resource<List<PhotoResponseItem>> =
        safeApiCall { api.getRandomPhotos(DEFAULT_PHOTO_COUNT) }

    suspend fun getSearchResult(
        searchQuery: String,
        page: Int,
        perPage: Int
    ): Resource<SearchResponse> =
        safeApiCall { api.getSearchResults(searchQuery, page, perPage) }

    suspend fun addOfflinePhoto(photo: OfflinePhoto): Long {
        return db.getOfflinePhotoDao().addOfflinePhoto(photo)
    }

    fun getOfflinePhotos() = db.getOfflinePhotoDao().getOfflinePhotos()
}