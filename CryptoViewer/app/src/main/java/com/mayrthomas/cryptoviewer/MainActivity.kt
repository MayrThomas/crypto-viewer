package com.mayrthomas.cryptoviewer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mayrthomas.cryptoviewer.data.CoinRepository
import com.mayrthomas.cryptoviewer.data.CoinRepositoryImpl
import com.mayrthomas.cryptoviewer.model.BaseCoin
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