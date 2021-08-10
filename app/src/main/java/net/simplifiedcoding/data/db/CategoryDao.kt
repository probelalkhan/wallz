package net.simplifiedcoding.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategories(categories: List<Category>)

    @Query("SELECT * FROM Categories")
    fun getCategories(): Flow<List<Category>>

}