package com.example.notekit.note_details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun NoteDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    navigateToNotesScreen: () -> Unit,
    navigateToSaveNoteScreen: (String) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NoteDetailsScreen(
        modifier = modifier,
        uiState = uiState,
        onArrowBackIconClick = navigateToNotesScreen,
        onDeleteIconClick = {
            viewModel.handleEvent(NoteDetailsUiEvent.OnDeleteClick(it))
            navigateToNotesScreen()
        },
        onEditIconClick = navigateToSaveNoteScreen,
    )
}