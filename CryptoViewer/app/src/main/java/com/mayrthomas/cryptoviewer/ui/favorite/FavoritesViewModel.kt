package com.mayrthomas.cryptoviewer.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayrthomas.cryptoviewer.data.FavoriteRepository
import com.mayrthomas.cryptoviewer.model.BaseCoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(private val favoriteRepository: FavoriteRepository): ViewModel() {
    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    var uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            favoriteRepository.getFavorites().collectLatest { favorites ->
                _uiState.value = FavoritesUiState.Success(favorites)
            }
        }
    }

    fun removeAsFavorite(coin: BaseCoin) {
        viewModelScope.launch {
            val result = favoriteRepository.removeFromFavorites(coin)

            if(result) {
                _uiState.update {
                    FavoritesUiState.Success(
                        favoriteRepository.getFavorites().first()
                    )
                }
            }
        }
    }
}