package com.mayrthomas.cryptoviewer.ui.navigation

sealed class Screen(val route: String) {
    data object Coins: Screen("coin_screen")
    data object Favorites: Screen("favorites_screen")
}