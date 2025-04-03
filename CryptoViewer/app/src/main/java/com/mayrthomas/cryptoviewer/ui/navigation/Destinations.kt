package com.mayrthomas.cryptoviewer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite

val navigationItems = listOf(
    NavigationItem(
        title = "Coins",
        icon = Icons.AutoMirrored.Filled.List,
        route = Screen.Coins.route
    ),
    NavigationItem(
        title = "Favorites",
        icon = Icons.Filled.Favorite,
        route = Screen.Favorites.route
    )
)