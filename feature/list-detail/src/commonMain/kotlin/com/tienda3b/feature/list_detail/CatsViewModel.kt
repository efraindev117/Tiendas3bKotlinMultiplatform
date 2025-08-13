package com.tienda3b.feature.list_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tienda3b.app.core.domain.usescase.cats.CatsUsesCase
import com.tienda3b.app.core.domain.usescase.favorites.FavoritesUseCases
import com.tienda3b.app.core.model.CatsUiModelItem
import com.tienda3b.app.core.model.favorite.FavoriteUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

class CatsViewModel(
    private val catsUsesCase: CatsUsesCase,
    private val favoritesUsesCase: FavoritesUseCases,
    private val dispatcher: CoroutineDispatcher
) : ViewModel(), KoinComponent {

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui

    val items: StateFlow<List<CatsUiModelItem>> =
        catsUsesCase.getCatsFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val favoriteIds: StateFlow<Set<String>> =
        favoritesUsesCase.observe()            // <- ya no es null
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptySet())

    val favoriteCats: StateFlow<List<FavoriteUi>> =
        favoritesUsesCase.observeList()                // <- usa tu ObserveFavoritesUseCase
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())


    val favoriteIdws: StateFlow<Set<String>> =
        favoritesUsesCase.observe()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptySet<String>()             // 👈 tipo explícito
            )

    private val _pending = MutableStateFlow<Set<String>>(emptySet())


    fun toggleFavorite(catId: String, name: String, image: String) {
        val isFavNow = favoriteIdws.value.contains(catId)
        _pending.update { set -> if (isFavNow) set - catId else set + catId }
        viewModelScope.launch(dispatcher) {
            try {
                if (isFavNow) favoritesUsesCase.remove(catId) else favoritesUsesCase.add(
                    catId,
                    name,
                    image
                )
            } finally {
                _pending.update { it - catId }
            }
        }
    }


    init {
        loadNext()
    }


    fun loadNext() = viewModelScope.launch(dispatcher) {
        _ui.update { it.copy(isLoading = true, error = null) }
        runCatching { catsUsesCase.loadNext() }
            .onFailure { _ui.update { it.copy(error = it.error ?: "Error", isLoading = false) } }
            .onSuccess { _ui.update { it.copy(isLoading = false) } }
    }

    fun refresh() = viewModelScope.launch(dispatcher) {
        _ui.update { it.copy(isLoading = true, error = null) }
        runCatching { catsUsesCase.refresh() }
            .onFailure { _ui.update { it.copy(error = it.error ?: "Error", isLoading = false) } }
            .onSuccess { _ui.update { it.copy(isLoading = false) } }
    }

    fun observeCatById(id: String): StateFlow<CatsUiModelItem?> =
        catsUsesCase.getCatById(id)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )
}