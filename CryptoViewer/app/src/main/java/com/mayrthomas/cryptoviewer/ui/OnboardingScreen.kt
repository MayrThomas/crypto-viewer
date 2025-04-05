package com.mayrthomas.cryptoviewer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayrthomas.cryptoviewer.R
import com.mayrthomas.cryptoviewer.ui.theme.CryptoViewerTheme
import com.mayrthomas.cryptoviewer.ui.views.CVCheckbox

@Composable
fun OnboardingScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        val currencyCodes = stringArrayResource(R.array.currency_codes)
//        val currencyNames = stringArrayResource(R.array.currency_names)
//        val currencySymbols = stringArrayResource(R.array.currency_symbol)

        val currencyCodes = listOf("USD", "EUR", "AUD")
        val currencyNames = listOf("United States Dollar", "Euro", "Australian Dollar")
        val currencySymbols = listOf("$", "€", "$")


        Spacer(modifier = Modifier.width(48.dp))

        Text("Select your preferred currency")

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(currencyCodes) { index, _ ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    var isChecked by remember { mutableStateOf(false) }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CVCheckbox(isChecked, onCheckedChange = { isChecked = it} )
                        Text(currencySymbols[index], modifier = Modifier.padding(horizontal = 8.dp))
                        Text(currencyCodes[index])
                        Spacer(modifier = Modifier.weight(1f))
                        Text(currencyNames[index], modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    CryptoViewerTheme {
        Scaffold {
            OnboardingScreen(it)
        }
    }
}