package net.simplifiedcoding.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class Category(
    val name: String,
    val thumb: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}