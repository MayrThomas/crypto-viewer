package com.mayrthomas.cryptoviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mayrthomas.cryptoviewer.data.CoinRepositoryImpl
import com.mayrthomas.cryptoviewer.network.RetrofitBuilder
import com.mayrthomas.cryptoviewer.ui.coins.CoinsScreen
import com.mayrthomas.cryptoviewer.ui.coins.CoinsViewmodel
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val tmp = true

        val coinRepository = CoinRepositoryImpl(RetrofitBuilder.createRetrofit())

        setContent {
            CryptoViewerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CoinsScreen(innerPadding, CoinsViewmodel(coinRepository))
                }
            }
        }
    }
}