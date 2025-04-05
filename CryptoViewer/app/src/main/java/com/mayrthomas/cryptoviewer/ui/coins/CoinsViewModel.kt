package com.mayrthomas.cryptoviewer.ui.coins

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayrthomas.cryptoviewer.data.CoinRepository
import com.mayrthomas.cryptoviewer.storage.UserPreferencesDataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoinsViewModel(private val coinRepository: CoinRepository, userPreferencesDataStoreManager: UserPreferencesDataStoreManager): ViewModel() {
    private val _uiState = MutableStateFlow<CoinsUiState>(CoinsUiState.Loading)
    var uiState: StateFlow<CoinsUiState> = _uiState.asStateFlow()

    init {
        // immediately fetch coin data
        _uiState.value = CoinsUiState.Loading
        viewModelScope.launch {
            try {
                val currencyKey = userPreferencesDataStoreManager
                    .getStringPreference(UserPreferencesDataStoreManager.CURRENCY_KEY)

                Log.w("CURRENCY", currencyKey)

                coinRepository.getCoins(currencyKey).collectLatest {
                    _uiState.value = CoinsUiState.Success(it)
                }

            } catch (ex: Exception) {
                _uiState.value = CoinsUiState.Error
            }
        }
    }
}