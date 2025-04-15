package com.mayrthomas.cryptoviewer.feature.coindetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayrthomas.cryptoviewer.model.Description
import com.mayrthomas.cryptoviewer.model.DetailCoin
import com.mayrthomas.cryptoviewer.model.ImageData
import com.mayrthomas.cryptoviewer.model.Links
import com.mayrthomas.cryptoviewer.model.MarketData
import com.mayrthomas.cryptoviewer.model.Prices
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme
import com.mayrthomas.cryptoviewer.ui.views.CoinDetailFooter
import com.mayrthomas.cryptoviewer.ui.views.CoinDetailHeader
import com.mayrthomas.cryptoviewer.ui.views.CoinDetailMarketView

@Composable
fun CoinDetailScreen(padding: PaddingValues, viewModel: CoinDetailViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    CoinDetailScreen(padding, uiState.value)
}

@Composable
private fun CoinDetailScreen(padding: PaddingValues, uiState: CoinDetailUiState) {
    Box(
        modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)
    ) {
        when(uiState) {
            is CoinDetailUiState.Loading -> {}
            is CoinDetailUiState.Error -> {}
            is CoinDetailUiState.Success -> {

                // Show Coin Detail Data
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    CoinDetailHeader(uiState.coinData)
                    CoinDetailMarketView(uiState.coinData.marketData)
                    Text(uiState.coinData.description.en)
                }

                CoinDetailFooter(
                    uiState.coinData.links,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Preview
@Composable
fun CoinDetailScreenPreview() {
    CryptoViewerTheme {
        Surface {
            CoinDetailScreen(
                PaddingValues(4.dp),
                CoinDetailUiState.Success(
                    DetailCoin(
                        "",
                        "Test Coin",
                        "TC",
                        Description("This is a very simple Test Coin to showcase the ui"),
                        Links(
                            listOf("1234"),
                            "5678",
                            "98765"
                        ),
                        ImageData(
                            "",
                            "",
                            ""
                        ),
                        MarketData(
                            Prices(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f, 0.0f,0.0f),
                            0.0f,
                            0.0f,
                            0.0f,
                            0.0f
                        )
                    )
                )
            )
        }
    }
}