package com.example.notekit.core.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.notekit.ui.theme.NoteKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Normal
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@Preview
@Composable
private fun AppTopBarPreview() {
    NoteKitTheme(dynamicColor = false) {
        AppTopBar(
            title = "Note Kit",
            modifier = Modifier,
            navigationIcon = {},
            actions = {},
        )
    }
}