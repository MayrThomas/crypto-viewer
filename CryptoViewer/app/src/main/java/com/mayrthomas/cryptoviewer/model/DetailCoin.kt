package com.mayrthomas.cryptoviewer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DetailCoin(
    val id: String,
    val name: String,
    val symbol: String,
    val description: Description,
    val links: Links,
    val image: ImageData,
    @SerializedName("market_data")
    val marketData: MarketData
)
