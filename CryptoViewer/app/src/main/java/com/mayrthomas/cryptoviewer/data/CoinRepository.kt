package com.mayrthomas.cryptoviewer.data

import com.mayrthomas.cryptoviewer.model.BaseCoin
import com.mayrthomas.cryptoviewer.model.DetailCoin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<List<BaseCoin>>

    suspend fun getCoinData(id: String): DetailCoin
}