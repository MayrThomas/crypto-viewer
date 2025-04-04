package com.mayrthomas.cryptoviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailCoin(
    val id: String,
    val name: String,
    val symbol: String,
    val description: Description,
    val links: Links,
    val image: ImageData,
    val marketData: MarketData
)
