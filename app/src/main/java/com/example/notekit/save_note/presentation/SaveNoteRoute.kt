package com.example.notekit.save_note.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SaveNoteRoute(
    modifier: Modifier = Modifier,
    viewModel: SaveNoteViewModel = hiltViewModel(),
    topBarTitle: Int?,
    navigateToNotesScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SaveNoteScreen(
        modifier = modifier,
        uiState = uiState,
        topBarTitle = topBarTitle,
        onIconArrowBackClick = navigateToNotesScreen,
        onIconDoneClick = { viewModel.handleEvent(SaveNoteUiEvent.OnSaveClicked) },
        navigateToNotesScreen = navigateToNotesScreen,
        onNoteNameChanged = { viewModel.handleEvent(SaveNoteUiEvent.OnNoteNameChanged(it)) },
        onNoteContentChanged = { viewModel.handleEvent(SaveNoteUiEvent.OnNoteContentChanged(it)) }
    )
}