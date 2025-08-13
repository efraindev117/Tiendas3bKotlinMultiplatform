package com.tienda3b.app.core.domain.usescase.favorites

data class FavoritesUseCases(
    val add: AddFavoriteUseCase,
    val remove: RemoveFavoriteUseCase,
    val observe: ObserveFavoriteIdsUseCase,
    val toggle: ToggleFavoriteUseCase,
    val observeList: ObserveFavoritesUseCase
)
