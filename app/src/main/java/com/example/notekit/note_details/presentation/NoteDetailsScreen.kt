package com.example.notekit.note_details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notekit.core.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: NoteDetailsUiState,
    onIconArrowBackClick: () -> Unit,
    onIconDeleteClick: (Note) -> Unit,
    onIconEditClick: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Note Details",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Nav to NotesScreen */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Nav Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Delete item and Nav to NotesScreen */ }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete item"
                        )
                    }
                    IconButton(onClick = { /* TODO: Nav to SaveNoteScreen */ }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit item"
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
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
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = name
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
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

    NoteDetailsScreen(
        uiState = uiState,
        onIconArrowBackClick = {},
        onIconDeleteClick = {},
        onIconEditClick = {},
    )
}