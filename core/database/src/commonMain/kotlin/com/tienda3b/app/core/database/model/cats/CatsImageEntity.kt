package com.tienda3b.app.core.database.model.cats

import com.tienda3b.app.core.model.Image

data class CatsImageEntity(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String
)

fun CatsImageEntity.toDomain(): Image =
    Image(
        id     = id,
        width  = width,
        height = height,
        url    = url
    )