package com.example.notekit.search_note.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
internal fun SearchNoteRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchNoteViewModel = hiltViewModel(),
    navigateToNotesScreen: () -> Unit,
    navigateToNoteDetailsScreen: (String) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    SearchNoteScreen(
        modifier = modifier,
        state = state,
        onArrowBackIconClick = navigateToNotesScreen,
        onQueryChange = { newQuery ->
            viewModel.handleIntent(SearchNoteScreenIntent.PerformSearch(newQuery))
        },
        onClearIconClick = { viewModel.handleIntent(SearchNoteScreenIntent.ClearSearch) },
        onNoteClick = navigateToNoteDetailsScreen,
    )
}