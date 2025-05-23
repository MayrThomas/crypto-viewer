package com.mayrthomas.cryptoviewer.feature.favorite

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
import com.mayrthomas.cryptoviewer.ui.views.EmptyFavorites

@Composable
fun FavoriteScreen(
    padding: PaddingValues,
    viewmodel: FavoritesViewModel,
    onItemClicked: (String) -> Unit,
    goToOverviewClicked: () -> Unit
) {
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        when(uiState.value) {
            is FavoritesUiState.Loading -> {
                AnimatedPreloader(
                    modifier = Modifier.size(200.dp).align(Alignment.Center),
                    R.raw.loading_animation
                )
            }
            is FavoritesUiState.Error -> {}
            is FavoritesUiState.Success -> {
                val favorites = (uiState.value as FavoritesUiState.Success).favorites

                if(favorites.isEmpty()) {
                    EmptyFavorites(goToOverviewClicked)
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        items(favorites) { coin ->
                            CoinListItem(coin,  false, false, onItemClicked)
                        }
                    }
                }
            }
        }
    }
}