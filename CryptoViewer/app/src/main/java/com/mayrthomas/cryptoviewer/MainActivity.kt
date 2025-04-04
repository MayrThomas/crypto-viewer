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
import androidx.navigation.toRoute
import com.mayrthomas.cryptoviewer.data.CoinRepositoryImpl
import com.mayrthomas.cryptoviewer.data.FavoriteRepositoryImpl
import com.mayrthomas.cryptoviewer.network.RetrofitBuilder
import com.mayrthomas.cryptoviewer.storage.FavoritesDataStoreManager
import com.mayrthomas.cryptoviewer.ui.coindetail.CoinDetailScreen
import com.mayrthomas.cryptoviewer.ui.coindetail.CoinDetailViewModel
import com.mayrthomas.cryptoviewer.ui.coins.CoinsScreen
import com.mayrthomas.cryptoviewer.ui.coins.CoinsViewModel
import com.mayrthomas.cryptoviewer.ui.favorite.FavoriteScreen
import com.mayrthomas.cryptoviewer.ui.favorite.FavoritesViewModel
import com.mayrthomas.cryptoviewer.ui.navigation.Screen
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme
import com.mayrthomas.cryptoviewer.ui.views.CVNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // TODO add dependency injection
        val coinRepository = CoinRepositoryImpl(RetrofitBuilder.createRetrofit())
        val favoriteRepository = FavoriteRepositoryImpl(FavoritesDataStoreManager(baseContext))

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
                            CoinsScreen(innerPadding, CoinsViewModel(coinRepository, favoriteRepository)) { id ->
                                navController.navigate(Screen.CoinDetail(id))
                            }
                        }
                        composable(route = Screen.Favorites.route) {
                            FavoriteScreen(innerPadding, FavoritesViewModel(favoriteRepository)) { id ->
                                navController.navigate(Screen.CoinDetail(id))
                            }
                        }
                        composable<Screen.CoinDetail> { backStackEntry ->
                            val route = backStackEntry.toRoute<Screen.CoinDetail>()
                            CoinDetailScreen(innerPadding, CoinDetailViewModel(coinRepository, route.coinID))
                        }
                    }
                }
            }
        }
    }
}