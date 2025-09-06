package com.example.notekit.core.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.notekit.ui.theme.NoteKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    titleContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    title: String
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = titleContentColor,
            containerColor = containerColor
        )
    )
}

@Preview
@Composable
private fun AppTopBarPreview() {
    NoteKitTheme(dynamicColor = false) {
        AppTopBar(
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            title = "Note Kit"
        )
    }
}