package com.mayrthomas.cryptoviewer.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteScreen(padding: PaddingValues) {
    Box(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        Icon(
            modifier = Modifier.size(50.dp).align(Alignment.Center),
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite Icon"
        )
    }
}