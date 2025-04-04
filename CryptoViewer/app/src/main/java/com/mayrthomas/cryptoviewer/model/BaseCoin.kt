package com.mayrthomas.cryptoviewer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseCoin(
    val id: String,
    val symbol: String,
    val name: String,
    @SerializedName("current_price")
    val currentPrice: Float,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Float,
    val image: String
)
