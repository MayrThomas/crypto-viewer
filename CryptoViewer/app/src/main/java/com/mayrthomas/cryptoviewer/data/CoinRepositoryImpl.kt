package com.mayrthomas.cryptoviewer.data

import com.mayrthomas.cryptoviewer.model.BaseCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val retrofit: Retrofit): CoinRepository {

    private val coinApi: CoinApi = retrofit.create(CoinApi::class.java)

    override fun getCoins(): Flow<List<BaseCoin>> = flow {
        val coins = coinApi.getCoinsList()
        emit(coins)
    }
}

interface CoinApi {

    @GET("coins/markets?vs_currency=usd&order=market_cap_des")
    suspend fun getCoinsList(): List<BaseCoin>
}