package com.bayu07750.albumsplaceholderkmp.core.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("albumId")
    val albumId: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String?
)