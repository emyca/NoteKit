package com.example.notekit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notekit.R
import com.example.notekit.core.domain.model.Note
import com.example.notekit.ui.viewmodel.NotesScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotesScreen(
    modifier: Modifier = Modifier,
    onAddNote: () -> Unit = {},
    uiState: NotesScreenUiState,
    onNoteClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.notes),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNote,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_note)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when(uiState) {
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
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(R.string.tap_plus_btn_add_note),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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

@Composable
private fun NoteItem(
    modifier: Modifier = Modifier,
    title: String,
) {
    Row(
        modifier = modifier
            .height(72.dp)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier.padding(start = 4.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = colorResource(R.color.black),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
