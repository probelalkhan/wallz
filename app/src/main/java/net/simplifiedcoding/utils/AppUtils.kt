package net.simplifiedcoding.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import net.simplifiedcoding.data.Category


const val GRID_COLUMN_SIZE = 2
const val NOTIFICATION_CHANNEL_ID = "WallzNotifications"
const val DEFAULT_PHOTO_COUNT = 30
const val DEFAULT_PHOTO_EXTENSION = ".jpg"

fun getLocalCategories(): List<Category> {
    return listOf(
        Category("Abstract", "Abstract.jpeg"),
        Category("Amoled", "Amoled.jpeg"),
        Category("Anime", "Anime.jpeg"),
        Category("Art", "Art.jpeg"),
        Category("Cars", "Cars.jpeg"),
        Category("City", "City.jpeg"),
        Category("Dark", "Dark.jpeg"),
        Category("Flowers", "Flowers.jpeg"),
        Category("Food", "Food.jpeg"),
        Category("Love", "Love.jpeg"),
        Category("Minimal", "Minimal.jpeg"),
        Category("Motorcycles", "Motorcycles.jpeg"),
        Category("Music", "Music.jpeg"),
        Category("Nature", "Nature.jpeg"),
        Category("Space", "Space.jpeg"),
        Category("Sports", "Sports.jpeg"),
        Category("Technology", "Technology.jpeg")
    )
}

fun Context.getBitmapFromAsset(name: String): Bitmap? {
    val istr = assets.open(name)
    val bitmap = BitmapFactory.decodeStream(istr)
    istr.close()
    return bitmap
}

