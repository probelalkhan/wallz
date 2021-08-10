package net.simplifiedcoding.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Category::class], version = 1)
abstract class WallzDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    companion object {
        private const val DB_NAME = "WallzDb"

        @Volatile
        private var instance: WallzDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WallzDatabase::class.java,
                DB_NAME
            ).build()
    }
}