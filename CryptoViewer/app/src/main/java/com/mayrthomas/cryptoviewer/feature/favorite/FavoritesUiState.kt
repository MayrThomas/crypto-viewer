package com.mayrthomas.cryptoviewer.feature.favorite

import com.mayrthomas.cryptoviewer.model.BaseCoin

sealed interface FavoritesUiState {
    data object Loading: FavoritesUiState
    data object Error: FavoritesUiState
    data class Success(val favorites: List<BaseCoin>): FavoritesUiState
}