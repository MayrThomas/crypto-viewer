package com.mayrthomas.cryptoviewer.ui.coindetail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CoinDetailScreen(padding: PaddingValues, viewModel: CoinDetailViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        when(uiState.value) {
            is CoinDetailUiState.Loading -> {}
            is CoinDetailUiState.Error -> {}
            is CoinDetailUiState.Success -> {

                // Show Coin Detail Data
                val coin = (uiState.value as CoinDetailUiState.Success).coinData
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Text(coin.name)
                    Text(coin.description.en)
                    Text(coin.links.homepage[0])
                    Text(coin.links.whitepaper)
                    Text(coin.links.subreddit)
                }
            }
        }
    }
}