package com.mayrthomas.cryptoviewer.ui.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayrthomas.cryptoviewer.data.CoinRepository
import com.mayrthomas.cryptoviewer.data.FavoriteRepository
import com.mayrthomas.cryptoviewer.model.BaseCoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinsViewmodel(private val coinRepository: CoinRepository, private val favoriteRepository: FavoriteRepository): ViewModel() {
    private val _uiState = MutableStateFlow<CoinsUiState>(CoinsUiState.Loading)
    var uiState: StateFlow<CoinsUiState> = _uiState.asStateFlow()

    private lateinit var coinList: List<BaseCoin>

    init {
        // immediately fetch coin data
        _uiState.value = CoinsUiState.Loading
        viewModelScope.launch {
            try {
                coinList = coinRepository.getCoins().first()
                val favorites = favoriteRepository.getFavorites().first().map { it.id }

                _uiState.value = CoinsUiState.Success(coinList, favorites)

            } catch (ex: Exception) {
                _uiState.value = CoinsUiState.Error
            }
        }
    }

    fun addAsFavorite(coin: BaseCoin) {
        viewModelScope.launch {
            val result = favoriteRepository.addToFavorites(coin)

            if(result) {
                _uiState.update {
                    CoinsUiState.Success(
                        coinList,
                        favoriteRepository.getFavorites().first().map { it.id }
                    )
                }
            }
        }
    }

    fun removeAsFavorite(coin: BaseCoin) {
        viewModelScope.launch {
            val result = favoriteRepository.removeFromFavorites(coin)

            if(result) {
                _uiState.update {
                    CoinsUiState.Success(
                        coinList,
                        favoriteRepository.getFavorites().first().map { it.id }
                    )
                }
            }
        }
    }
}