package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayrthomas.cryptoviewer.model.MarketData
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinDetailMarketView(marketData: MarketData, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text("${marketData.currentPrice[0].second}")
        Text(text = "${marketData.changePercentage24h} %")

        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.LightGray)) {
            drawLine(Color.Blue, Offset(0F, 0F), Offset(300F, 300F))
            drawLine(Color.Blue, Offset(300F, 300F), Offset(450F, 200F))
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
                    listOf(Pair("Test", 0.0f)),
                    0.0f,
                    0.0f,
                    0.0f,
                    0.0f
                )
            )
        }
    }
}