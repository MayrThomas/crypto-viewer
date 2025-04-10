package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mayrthomas.cryptoviewer.model.Links
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinDetailFooter(links: Links, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {

            }
        ) {
            Text("Website")
        }
        Button(
            onClick = {

            }
        ) {
            Text("Whitepaper")
        }
        Button(
            onClick = {

            }
        ) {
            Icon(Icons.Default.Face, contentDescription = links.subreddit)
        }
    }
}

@Preview
@Composable
fun CoinDetailFooterPreview() {
    CryptoViewerTheme {
        Surface {
            CoinDetailFooter(
                Links(listOf("1234"), "5678", "98765")
            )
        }
    }
}