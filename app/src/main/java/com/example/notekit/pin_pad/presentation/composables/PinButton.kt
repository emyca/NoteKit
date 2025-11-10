package com.example.notekit.pin_pad.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
private fun PinButton(
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(100),
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape)
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(100)
            )
            .clickable(onClick = onClick)
            .defaultMinSize(minWidth = 95.dp, minHeight = 95.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.secondary) {
            content()
        }
    }
}