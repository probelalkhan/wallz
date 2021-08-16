package net.simplifiedcoding.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfflinePhoto(
    val uri: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}