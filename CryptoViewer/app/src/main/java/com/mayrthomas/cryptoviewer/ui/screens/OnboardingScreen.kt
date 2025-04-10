package com.mayrthomas.cryptoviewer.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.mayrthomas.cryptoviewer.R
import com.mayrthomas.cryptoviewer.storage.UserPreferencesDataStoreManager
import com.mayrthomas.cryptoviewer.ui.views.CVCheckbox

@Composable
fun OnboardingScreen(padding: PaddingValues, userPreferencesDataStoreManager: UserPreferencesDataStoreManager, navigateToMainScreen: () -> Unit) {
    if(!userPreferencesDataStoreManager.isCurrencyAlreadySet()) {
        Log.w("OnboardingScreen", "currency already set")
        navigateToMainScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        val currencyCodes = stringArrayResource(R.array.currency_codes)
        val currencyNames = stringArrayResource(R.array.currency_names)
        val currencySymbols = stringArrayResource(R.array.currency_symbol)

        val (selectedOption, onSelectionChange) = remember { mutableStateOf("") }


        Spacer(modifier = Modifier.height(32.dp))

        Text("Select your preferred currency")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(currencyCodes) { index, _ ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            selected = selectedOption == currencyCodes[index],
                            onClick = { onSelectionChange(currencyCodes[index]) },
                            role = Role.RadioButton
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CVCheckbox(checked = selectedOption == currencyCodes[index], onCheckedChange = { onSelectionChange(currencyCodes[index]) } )
                        Text(text ="(${currencySymbols[index]})", modifier = Modifier.padding(horizontal = 8.dp))
                        Text(text = currencyCodes[index])
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = currencyNames[index], modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 24.dp, top = 8.dp, end = 16.dp)
        ) {
            Button(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick =  {
                    userPreferencesDataStoreManager.setCurrency(selectedOption.lowercase())
                    navigateToMainScreen()
                },
                enabled = currencyCodes.contains(selectedOption)
            ) {
                Text("Continue")
            }
        }
    }
}