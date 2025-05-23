package com.mayrthomas.cryptoviewer.feature.coins

import com.mayrthomas.cryptoviewer.model.BaseCoin

sealed interface CoinsUiState {
    data object Loading: CoinsUiState
    data object Error: CoinsUiState
    data class Success(val coins: List<BaseCoin>): CoinsUiState
}
