package com.mayrthomas.cryptoviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageData(
    val thumb: String,
    val small: String,
    val large: String
)
