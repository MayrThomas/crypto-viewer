package com.mayrthomas.cryptoviewer.ui.coins

import com.mayrthomas.cryptoviewer.model.BaseCoin

sealed interface CoinsUiState {
    data object Loading: CoinsUiState
    data object Error: CoinsUiState
    data class Success(
        val coins: List<BaseCoin>,
        val favorites: List<String>
    ): CoinsUiState
}
