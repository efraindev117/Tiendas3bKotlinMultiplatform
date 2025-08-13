package com.tienda3b.app.core.domain.usescase.cats

data class CatsUsesCase(
    val getCatsFlow: GetCatsFlowUseCase,
    val loadNext: LoadNextCatsUseCase,
    val refresh: RefreshCatsUseCase,
    val getCatById: GetCatByIdUseCase
)