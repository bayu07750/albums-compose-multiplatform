package com.bayu07750.albumsplaceholderkmp.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val userId: Int?,
    val title: String?
)