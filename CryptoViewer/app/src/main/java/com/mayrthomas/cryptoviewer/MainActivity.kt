package com.mayrthomas.cryptoviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mayrthomas.cryptoviewer.data.CoinRepositoryImpl
import com.mayrthomas.cryptoviewer.data.FavoriteRepositoryImpl
import com.mayrthomas.cryptoviewer.network.RetrofitBuilder
import com.mayrthomas.cryptoviewer.storage.FavoritesDataStoreManager
import com.mayrthomas.cryptoviewer.storage.UserPreferencesDataStoreManager
import com.mayrthomas.cryptoviewer.feature.coindetail.CoinDetailScreen
import com.mayrthomas.cryptoviewer.feature.coindetail.CoinDetailViewModel
import com.mayrthomas.cryptoviewer.feature.coins.CoinsScreen
import com.mayrthomas.cryptoviewer.feature.coins.CoinsViewModel
import com.mayrthomas.cryptoviewer.feature.favorite.FavoriteScreen
import com.mayrthomas.cryptoviewer.feature.favorite.FavoritesViewModel
import com.mayrthomas.cryptoviewer.ui.navigation.Screen
import com.mayrthomas.cryptoviewer.ui.navigation.navigationItems
import com.mayrthomas.cryptoviewer.ui.screens.OnboardingScreen
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme
import com.mayrthomas.cryptoviewer.ui.views.CVNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // TODO add dependency injection
        val userPreferencesDataStoreManager = UserPreferencesDataStoreManager(baseContext)
        val coinRepository = CoinRepositoryImpl(RetrofitBuilder.createRetrofit())
        val favoriteRepository = FavoriteRepositoryImpl(FavoritesDataStoreManager(baseContext))

        setContent {
            val navController = rememberNavController()

            val selectedNavigationIndex = rememberSaveable {
                mutableIntStateOf(0)
            }

            CryptoViewerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { CVNavigationBar(navController, selectedNavigationIndex) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Onboarding.route
                    ) {
                        composable(route = Screen.Onboarding.route) {
                            OnboardingScreen(innerPadding, userPreferencesDataStoreManager) {
                                navController.navigate(Screen.Coins.route)
                            }
                        }

                        composable(route = Screen.Coins.route) {
                            CoinsScreen(innerPadding, CoinsViewModel(coinRepository, userPreferencesDataStoreManager)) { id ->
                                navController.navigate(Screen.CoinDetail(id))
                            }
                        }
                        composable(route = Screen.Favorites.route) {
                            FavoriteScreen(
                                innerPadding,
                                FavoritesViewModel(favoriteRepository),
                                goToOverviewClicked = {
                                    selectedNavigationIndex.intValue = navigationItems.indexOfFirst { it.route == Screen.Coins.route }
                                    navController.navigate(Screen.Coins.route)
                                },
                                onItemClicked = { id ->  navController.navigate(Screen.CoinDetail(id)) }
                            )
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