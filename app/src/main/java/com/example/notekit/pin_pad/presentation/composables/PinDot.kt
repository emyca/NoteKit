package com.example.notekit.pin_pad.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp

@Composable
private fun PinDot(isFilled: Boolean) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(30.dp)
            .background(
                if (isFilled) MaterialTheme.colorScheme.tertiary else
                    if (isSystemInDarkTheme()) White
                    else Color.Gray, CircleShape
            )
    )
}