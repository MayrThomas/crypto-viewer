package com.mayrthomas.cryptoviewer.data

import com.mayrthomas.cryptoviewer.model.BaseCoin
import com.mayrthomas.cryptoviewer.model.DetailCoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class CoinRepositoryImpl (private val retrofit: Retrofit): CoinRepository {

    private val coinApi: CoinApi = retrofit.create(CoinApi::class.java)

    override fun getCoins(): Flow<List<BaseCoin>> = flow {
        // delay added to check loading animation
        delay(2000)
        val coins = coinApi.getCoinsList()
        emit(coins)
    }

    override suspend fun getCoinData(id: String): DetailCoin {
        return coinApi.getCoinData(id)
    }
}

interface CoinApi {

    @GET("coins/markets?vs_currency=usd&order=market_cap_des")
    suspend fun getCoinsList(): List<BaseCoin>

    @GET("coins/{coinID}")
    suspend fun getCoinData(@Path("coinID") coinId: String): DetailCoin
}