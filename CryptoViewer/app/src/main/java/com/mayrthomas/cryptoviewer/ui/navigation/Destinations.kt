package com.mayrthomas.cryptoviewer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.res.stringResource
import com.mayrthomas.cryptoviewer.R

val navigationItems = listOf(
    NavigationItem(
        title = R.string.navigation_coins,
        icon = Icons.AutoMirrored.Filled.List,
        route = Screen.Coins.route
    ),
    NavigationItem(
        title = R.string.navigation_favorite,
        icon = Icons.Filled.Favorite,
        route = Screen.Favorites.route
    )
)