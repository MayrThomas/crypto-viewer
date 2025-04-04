package com.mayrthomas.cryptoviewer.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val homepage: List<String>,
    val whitepaper: String,
    @SerializedName("subreddit_url")
    val subreddit: String
)
