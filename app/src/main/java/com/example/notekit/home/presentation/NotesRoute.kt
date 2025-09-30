package com.example.notekit.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun NotesRoute(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = hiltViewModel(),
    navigateToSaveNoteScreen: () -> Unit,
    navigateToNoteDetailsScreen: (String) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotesScreen(
        modifier = modifier,
        uiState = uiState,
        onAddItemFABClick = navigateToSaveNoteScreen,
        onNoteClick = navigateToNoteDetailsScreen,
    )
}