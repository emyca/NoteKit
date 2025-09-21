package com.example.notekit.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun NotesRoute(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = hiltViewModel(),
    navigateToInsertNote: () -> Unit,
    navigateToNoteDetails: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotesScreen(
        modifier = modifier,
        uiState = uiState,
        onAddItemFABClick = navigateToInsertNote,
        onNoteClick = navigateToNoteDetails
    )
}