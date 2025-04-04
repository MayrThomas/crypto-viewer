package com.mayrthomas.cryptoviewer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MarketData(
    @SerializedName("current_price")
    val currentPrice: List<Pair<String, Float>>,
    @SerializedName("price_change_percentage_24h")
    val changePercentage24h: Float,
    @SerializedName("price_change_percentage_7d")
    val changePercentage7d: Float,
    @SerializedName("price_change_percentage_30d")
    val changePercentage30d: Float,
    @SerializedName("price_change_percentage_1y")
    val changePercentage1y: Float,
)
