package com.bayu07750.albumsplaceholderkmp.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Album(
    @SerialName("id")
    val id: Int?,
    @SerialName("userId")
    val userId: Int?,
    @SerialName("title")
    val title: String?
)