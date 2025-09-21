package com.example.notekit.note_details.presentation

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notekit.R
import com.example.notekit.core.composables.AppTopBar
import com.example.notekit.ui.theme.NoteKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: NoteDetailsUiState,
    onArrowBackIconClick: () -> Unit,
    onDeleteIconClick: (Int) -> Unit,
    onEditIconClick: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.note_details_top_bar_title),
                navigationIcon = {
                    IconButton(onClick = { onArrowBackIconClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Nav Back",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onDeleteIconClick(NoteDetailsUiState.NoteState().id)
                        onArrowBackIconClick()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete item",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                    IconButton(onClick = { /* TODO: Nav to SaveNoteScreen */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit item",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            when (uiState) {
                is NoteDetailsUiState.NoteState -> NoteContent(
                    name = uiState.name,
                    content = uiState.content,
                )
            }
        }
    }
}

@Composable
private fun NoteContent(
    modifier: Modifier = Modifier,
    name: String,
    content: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = name
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            fontSize = 24.sp,
            text = content
        )
    }
}

@Composable
@Preview
private fun NoteDetailsScreenPreview() {

    val uiState = NoteDetailsUiState
        .NoteState(
            1, "My info", "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit"
        )
    NoteKitTheme(dynamicColor = false) {
        NoteDetailsScreen(
            uiState = uiState,
            onArrowBackIconClick = {},
            onDeleteIconClick = {},
            onEditIconClick = {},
        )
    }
}