package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayrthomas.cryptoviewer.model.Links
import com.mayrthomas.cryptoviewer.openCustomTab
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme

@Composable
fun CoinDetailFooter(links: Links, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if(links.homepage.isNotEmpty() && links.homepage[0].isNotBlank()) {
            Button(
                onClick = {
                    context.openCustomTab(links.homepage[0])
                },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text("Website")
            }
        }

        if(links.whitepaper.isNotBlank()) {
            Button(
                onClick = {
                    context.openCustomTab(links.whitepaper)
                },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text("Whitepaper")
            }
        }

        if(links.subreddit.isNotBlank()) {
            Button(
                onClick = {
                    context.openCustomTab(links.subreddit)
                },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(Icons.Default.Face, contentDescription = links.subreddit)
            }
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