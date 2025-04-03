package com.mayrthomas.cryptoviewer.ui.coins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayrthomas.cryptoviewer.R
import com.mayrthomas.cryptoviewer.ui.views.AnimatedPreloader
import com.mayrthomas.cryptoviewer.ui.views.CoinListItem

@Composable
fun CoinsScreen(padding: PaddingValues, viewmodel: CoinsViewmodel) {
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
       when(uiState.value) {
           is CoinsUiState.Loading -> {
                AnimatedPreloader(
                    modifier = Modifier.size(200.dp).align(Alignment.Center),
                    R.raw.loading_animation
                )
           }
           is CoinsUiState.Error -> {}
           is CoinsUiState.Success -> {

               val coins = (uiState.value as CoinsUiState.Success).coins
               val favorites = (uiState.value as CoinsUiState.Success).favorites

               LazyColumn(
                   modifier = Modifier.fillMaxSize().padding(16.dp),
                   verticalArrangement = Arrangement.spacedBy(24.dp)
               ) {
                   items(coins) { coin ->
                        val isFavorite = favorites.contains(coin.id)
                        CoinListItem(coin, isFavorite) {
                            if (isFavorite) viewmodel.removeAsFavorite(coin)
                            else viewmodel.addAsFavorite(coin)
                        }
                   }
               }
           }
       }
    }
}