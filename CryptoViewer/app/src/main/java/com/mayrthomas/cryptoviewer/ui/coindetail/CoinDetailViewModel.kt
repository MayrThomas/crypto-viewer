package com.mayrthomas.cryptoviewer.ui.coindetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayrthomas.cryptoviewer.data.CoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel(private val coinRepository: CoinRepository, coinID: String): ViewModel() {
    private val _uiState = MutableStateFlow<CoinDetailUiState>(CoinDetailUiState.Loading)
    var uiState: StateFlow<CoinDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = CoinDetailUiState.Loading
            try {
                val coin = coinRepository.getCoinData(coinID)
                _uiState.value = CoinDetailUiState.Success(coin)
            } catch (ex: Exception) {
                _uiState.value = CoinDetailUiState.Error
            }
        }
    }
}