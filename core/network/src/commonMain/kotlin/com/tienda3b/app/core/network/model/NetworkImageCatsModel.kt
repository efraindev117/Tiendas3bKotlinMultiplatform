package com.tienda3b.app.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkImageCatsModel(
    @SerialName("id")
    val id: String?,
    @SerialName("width")
    val width: Int?,
    @SerialName("height")
    val height: Int?,
    @SerialName("url")
    val url: String?
)