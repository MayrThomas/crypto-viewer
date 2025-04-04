package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.mayrthomas.cryptoviewer.ui.navigation.navigationItems
import com.mayrthomas.cryptoviewer.ui.theme.Cyan


@Composable
fun CVNavigationBar(navController: NavController, selectedNavigationIndex: MutableIntState) {
    NavigationBar {
        navigationItems.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(navigationItem.route)
                },
                icon = {
                    Icon(
                        imageVector = navigationItem.icon,
                        contentDescription = stringResource(navigationItem.title),
                        tint = if(selectedNavigationIndex.intValue == index) Cyan else Color.Gray
                    )
                },
                label = {
                    Text(
                        stringResource(navigationItem.title),
                        color = if (index == selectedNavigationIndex.intValue) Cyan
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    }
}