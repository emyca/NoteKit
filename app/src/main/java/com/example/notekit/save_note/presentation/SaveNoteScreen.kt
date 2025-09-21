package com.example.notekit.save_note.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notekit.R
import com.example.notekit.core.composables.AppFilledBtn
import com.example.notekit.core.composables.AppOutlinedTextField
import com.example.notekit.core.composables.AppTopBar
import com.example.notekit.ui.theme.NoteKitTheme

@Composable
internal fun SaveNoteScreen(
    modifier: Modifier = Modifier,
    uiState: SaveNoteUiState,
    topBarTitle: Int?,
    onArrowBackIconClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    onNoteNameChanged: (String) -> Unit,
    onNoteContentChanged: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            AppTopBar(
                title = stringResource(topBarTitle!!),
                navigationIcon = {
                    IconButton(onClick = { onArrowBackIconClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Nav Back",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                },
                actions = {},
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            when (uiState) {
                is SaveNoteUiState.NoteState -> NoteContent(
                    name = uiState.name,
                    content = uiState.content,
                    onNoteNameChanged = onNoteNameChanged,
                    onNoteContentChanged = onNoteContentChanged,
                    onSaveButtonClick = onSaveButtonClick,
                )
            }
        }
    }
}

// TODO: Add content scrolling?
@Composable
private fun NoteContent(
    modifier: Modifier = Modifier,
    name: String,
    content: String,
    onNoteNameChanged: (String) -> Unit,
    onNoteContentChanged: (String) -> Unit,
    onSaveButtonClick: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AppOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            value = name,
            labelText = stringResource(id = R.string.name_text_field_label),
            singleLine = true,
            onValueChange = onNoteNameChanged
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            value = content,
            labelText = stringResource(id = R.string.content_text_field_label),
            onValueChange = onNoteContentChanged
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppFilledBtn(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            text = stringResource(R.string.save_button_text),
            onClick = {
                if (name.isEmpty() || content.isEmpty()) {
                    Toast.makeText(
                        context, context.getText(R.string.add_data_toast_text),
                        Toast.LENGTH_LONG
                    ).show()
                    return@AppFilledBtn
                }
                onSaveButtonClick()
            }
        )
    }
}

@Composable
@Preview
private fun SaveNoteScreenPreview() {

    val uiState = SaveNoteUiState
        .NoteState(
            1, "My info", "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit"
        )
    NoteKitTheme(dynamicColor = false) {
        SaveNoteScreen(
            uiState = uiState,
            topBarTitle = R.string.insert_top_bar_title,
            onArrowBackIconClick = {},
            onSaveButtonClick = {},
            onNoteNameChanged = {},
            onNoteContentChanged = {}
        )
    }
}