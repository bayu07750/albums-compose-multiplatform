package com.bayu07750.albumsplaceholderkmp.core.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val albumId: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)