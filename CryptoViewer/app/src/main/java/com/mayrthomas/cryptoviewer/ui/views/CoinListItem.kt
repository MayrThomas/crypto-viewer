package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.mayrthomas.cryptoviewer.R
import com.mayrthomas.cryptoviewer.model.BaseCoin
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinListItem(coin: BaseCoin, isFirst: Boolean, isLast: Boolean, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onItemClicked(coin.id) },
        shape = if(isFirst)
                    RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                else if (isLast)
                    RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                else
                    RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
                modifier = Modifier.size(40.dp).clip(CircleShape)
            )

            Column {
                Text(if(coin.name.length < 20) coin.name else "${coin.name.take(20)}...")
                Text(coin.currentPrice.toString())
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(coin.symbol)
                Text(
                    "${coin.priceChangePercentage24h} %",
                    color = if(coin.priceChangePercentage24h < 0) Color.Red else Color.Green
                )
            }
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
                    1000f,
                    3.0025f,
                    ""),
                false,
                false,
            ) {}
        }
    }
}