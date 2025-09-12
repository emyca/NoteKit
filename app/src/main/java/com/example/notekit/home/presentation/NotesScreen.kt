package com.example.notekit.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notekit.R
import com.example.notekit.core.composables.AddItemFAB
import com.example.notekit.core.composables.AppTopBar
import com.example.notekit.core.composables.NoteItem
import com.example.notekit.core.domain.model.Note
import com.example.notekit.ui.theme.NoteKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotesScreen(
    modifier: Modifier = Modifier,
    uiState: NotesScreenUiState,
    onAddItemFABClick: () -> Unit,
    onNoteClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.app_name),
                modifier = Modifier.shadow(4.dp),
                navigationIcon = {},
                actions = {}
            )
        },
        floatingActionButton = {
            AddItemFAB {
                onAddItemFABClick()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is NotesScreenUiState.Empty -> NotesScreenEmpty()
                is NotesScreenUiState.Content -> NotesScreenContent(
                    notes = uiState.notes,
                    onNoteClick = onNoteClick
                )
            }
        }
    }
}

@Composable
private fun NotesScreenEmpty(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.no_data_yet),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(R.string.tap_plus_btn_add_note),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun NotesScreenContent(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onNoteClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(notes) { index, note ->
            Spacer(modifier = Modifier.height(24.dp))
            NoteItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onNoteClick(note.id.toString()) },
                title = note.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesScreenEmptyPreview() {
    NoteKitTheme(dynamicColor = false) {
        NotesScreenEmpty()
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesScreenContentPreview() {
    val notes = listOf(
        Note(1, "Alfa", "Info alfa"),
        Note(2, "Beta", "Info beta"),
        Note(3, "Gamma", "Info gamma"),
        Note(4, "Epsilon", "Info epsilon")
    )
    NoteKitTheme(dynamicColor = false) {
        NotesScreenContent(
            notes = notes,
            onNoteClick = {}
        )
    }
}
