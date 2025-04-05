package com.mayrthomas.cryptoviewer.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CVCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    enabled: Boolean = true,
) {
    val color = MaterialTheme.colorScheme
    val imageVector = Icons.Filled.CheckCircle
    val tint = if (checked) color.primary.copy(alpha = 0.8f) else color.background.copy(alpha = 0.8f)
    val background = Color.Transparent

    IconButton(onClick = { onCheckedChange(!checked) },
        enabled = enabled) {

        Icon(imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape).size(24.dp),
            contentDescription = "checkbox")
    }
}

@Preview
@Composable
fun CVCheckboxPreviewIsChecked() {
   CVCheckbox(true, {})
}

@Preview
@Composable
fun CVCheckboxPreviewIsNotChecked() {
    CVCheckbox(false, {})
}