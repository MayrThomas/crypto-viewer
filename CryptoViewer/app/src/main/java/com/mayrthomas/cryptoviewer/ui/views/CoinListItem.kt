package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.mayrthomas.cryptoviewer.R
import com.mayrthomas.cryptoviewer.model.BaseCoin
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinListItem(
    coin: BaseCoin,
    isFavorite: Boolean,
    onItemClicked: (String) -> Unit,
    onFavoriteClicked: (BaseCoin) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClicked(coin.id) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            ImageRequest.Builder(LocalContext.current)
                .data(coin.image)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = "${coin.name} logo",
            placeholder = painterResource(R.drawable.image_loading),
            modifier = Modifier.size(40.dp)
        )
        Text(coin.symbol)
        Text(coin.name)

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { onFavoriteClicked(coin) },
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.favorite_icon)
            )
        }
    }
}

@Preview
@Composable
fun CoinListItemPreview() {
    CryptoViewerTheme {
        Surface {
            CoinListItem(
                BaseCoin(
                    "id",
                    "TST",
                    "Test Coin",
                    ""),
                false,
                onItemClicked = { }
            ) {}
        }
    }
}