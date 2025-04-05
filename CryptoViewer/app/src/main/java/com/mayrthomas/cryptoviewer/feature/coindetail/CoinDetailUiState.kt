package com.mayrthomas.cryptoviewer.feature.coindetail

import com.mayrthomas.cryptoviewer.model.DetailCoin

sealed interface CoinDetailUiState {
    data object Loading: CoinDetailUiState
    data object Error: CoinDetailUiState
    data class Success(val coinData: DetailCoin): CoinDetailUiState
}