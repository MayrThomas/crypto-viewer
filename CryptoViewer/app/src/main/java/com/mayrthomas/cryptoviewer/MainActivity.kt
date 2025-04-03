package com.mayrthomas.cryptoviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.mayrthomas.cryptoviewer.data.CoinRepositoryImpl
import com.mayrthomas.cryptoviewer.network.RetrofitBuilder
import com.mayrthomas.cryptoviewer.ui.coins.CoinsScreen
import com.mayrthomas.cryptoviewer.ui.coins.CoinsViewmodel
import com.mayrthomas.cryptoviewer.ui.favorite.FavoriteScreen
import com.mayrthomas.cryptoviewer.ui.navigation.Screen
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme
import com.mayrthomas.cryptoviewer.ui.views.CVNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val tmp = true

        val coinRepository = CoinRepositoryImpl(RetrofitBuilder.createRetrofit())

        setContent {
            val navController = rememberNavController()

            CryptoViewerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { CVNavigationBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Coins.route
                    ) {
                        composable(route = Screen.Coins.route) {
                            CoinsScreen(innerPadding, CoinsViewmodel(coinRepository))
                        }
                        composable(route = Screen.Favorites.route) {
                            FavoriteScreen(innerPadding)
                        }
                    }
                }
            }
        }
    }
}