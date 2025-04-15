package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayrthomas.cryptoviewer.model.MarketData
import com.mayrthomas.cryptoviewer.model.Prices
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinDetailMarketView(marketData: MarketData, modifier: Modifier = Modifier) {
    val isPositiveChange = marketData.changePercentage24h >= 0

    Column(
        modifier = modifier.fillMaxWidth().padding(4.dp),
    ) {
        Text("${marketData.currentPrice.usd}", style = MaterialTheme.typography.titleLarge)
        Text(
            text = "${if(isPositiveChange) "+" else "-"} ${marketData.changePercentage24h} %",
            color = if(isPositiveChange) Color.Green else Color.Red,
            style = MaterialTheme.typography.titleSmall
        )
        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp).padding(4.dp)) {
            drawLine(Color.Blue, Offset(0F, 0F), Offset(300F, 300F), pathEffect = PathEffect.cornerPathEffect(5f))
            drawLine(Color.Blue, Offset(300F, 300F), Offset(450F, 200F), pathEffect = PathEffect.cornerPathEffect(5f))
        }
    }
}

@Preview
@Composable
fun CoinDetailMarketViewPreview() {
    CryptoViewerTheme {
        Surface {
            CoinDetailMarketView(
                MarketData(
                    Prices(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f, 0.0f,0.0f),
                    0.0f,
                    0.0f,
                    0.0f,
                    0.0f
                )
            )
        }
    }
}