package com.tienda3b.app.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkWeightCatsModel(
    @SerialName("imperial")
    val imperial: String?,
    @SerialName("metric")
    val metric: String?
)