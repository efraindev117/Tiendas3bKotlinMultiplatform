package com.tienda3b.app.core.database.model.cats

import com.tienda3b.app.core.model.Weight

data class CatsWeightEntity(
    val imperial: String,
    val metric: String
)

fun CatsWeightEntity.toDomain(): Weight =
    Weight(
        imperial = imperial.orEmpty(),
        metric   = metric.orEmpty()
    )