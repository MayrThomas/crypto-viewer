package com.mayrthomas.cryptoviewer.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    data object Coins: Screen("coin_screen")
    data object Favorites: Screen("favorites_screen")
    @Serializable
    data class CoinDetail(val coinID: String): Screen("coin_detail")
}