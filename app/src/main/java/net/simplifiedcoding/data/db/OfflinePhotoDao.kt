package net.simplifiedcoding.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OfflinePhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOfflinePhoto(offlinePhoto: OfflinePhoto): Long

    @Query("SELECT * FROM OfflinePhoto")
    fun getOfflinePhotos(): Flow<List<OfflinePhoto>>

}