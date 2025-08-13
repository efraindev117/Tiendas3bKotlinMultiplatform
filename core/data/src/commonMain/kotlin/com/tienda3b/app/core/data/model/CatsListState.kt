package com.tienda3b.app.core.data.model

data class CatsListState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
    val nextPage: Int = 0
)
