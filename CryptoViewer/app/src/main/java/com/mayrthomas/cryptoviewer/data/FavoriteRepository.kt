package com.mayrthomas.cryptoviewer.data

import com.mayrthomas.cryptoviewer.model.BaseCoin
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavorites(): Flow<List<BaseCoin>>

    suspend fun addToFavorites(coin: BaseCoin): Boolean

    suspend fun removeFromFavorites(coin: BaseCoin): Boolean
}