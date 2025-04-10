package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.mayrthomas.cryptoviewer.R
import com.mayrthomas.cryptoviewer.model.Description
import com.mayrthomas.cryptoviewer.model.DetailCoin
import com.mayrthomas.cryptoviewer.model.ImageData
import com.mayrthomas.cryptoviewer.model.Links
import com.mayrthomas.cryptoviewer.model.MarketData
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinDetailHeader(detailCoin: DetailCoin, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            ImageRequest.Builder(LocalContext.current)
                .data(detailCoin.image.thumb)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = "${detailCoin.name} logo",
            placeholder = painterResource(R.drawable.image_loading),
            error = painterResource(R.drawable.image_loading),
            modifier = Modifier.size(70.dp).clip(CircleShape)
        )

        Column {
            Text(text = detailCoin.name, style = MaterialTheme.typography.titleLarge)
            Text(text = detailCoin.symbol, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun CoinDetailHeaderPreview() {
    CryptoViewerTheme {
        Surface {
            CoinDetailHeader(
                DetailCoin(
                    "",
                    "Test Coin",
                    "TC",
                    Description("This is a very simple Test Coin to showcase the ui"),
                    Links(listOf("1234"), "5678", "98765"),
                    ImageData("", "", ""),
                    MarketData(listOf(), 0.0f, 0.0f, 0.0f, 0.0f)
                )
            )
        }
    }
}