package com.example.notekit.core.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.notekit.ui.theme.NoteKitTheme

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    labelText: String,
    labelTextColor: Color = MaterialTheme.colorScheme.secondary,
    singleLine: Boolean = false,
    disabledBorderColor: Color = MaterialTheme.colorScheme.secondary,
    unfocusedBorderColor: Color = MaterialTheme.colorScheme.secondary,
    focusedBorderColor: Color = MaterialTheme.colorScheme.secondary,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TextStyle(textColor),
        label = {
            Text(
                text = labelText,
                color = labelTextColor
            )
        },
        singleLine = singleLine,
        shape = OutlinedTextFieldDefaults.shape,
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = disabledBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedBorderColor = focusedBorderColor
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AppOutlinedTextFieldPreview() {
    NoteKitTheme(dynamicColor = false) {
        AppOutlinedTextField(
            value = "My info",
            labelText = "Name",
            onValueChange = {}
        )
    }
}
