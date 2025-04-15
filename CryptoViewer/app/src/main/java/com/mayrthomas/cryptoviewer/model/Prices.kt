package com.mayrthomas.cryptoviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class Prices(
    val usd: Float,
    val eur: Float,
    val gbp: Float,
    val jpy: Float,
    val aud: Float,
    val cad: Float,
    val chf: Float,
    val cny: Float,
    val inr: Float
)
