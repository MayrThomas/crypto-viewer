package com.mayrthomas.cryptoviewer.data

import com.mayrthomas.cryptoviewer.model.BaseCoin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<List<BaseCoin>>
}