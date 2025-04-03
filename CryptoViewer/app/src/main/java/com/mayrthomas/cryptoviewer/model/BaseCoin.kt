package com.mayrthomas.cryptoviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseCoin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String
)
