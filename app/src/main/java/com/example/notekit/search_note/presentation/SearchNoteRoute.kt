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
    val query by viewModel.query.collectAsState()

    SearchNoteScreen(
        modifier = modifier,
        state = state,
        query = query,
        onArrowBackIconClick = navigateToNotesScreen,
        onQueryChange = { newQuery ->
            viewModel.handleIntent(SearchNoteScreenIntent.UpdateQuery(newQuery))
        },
        onSearch = { submittedQuery ->
            viewModel.handleIntent(SearchNoteScreenIntent.Search(submittedQuery))
        },
        onClearIconClick = { viewModel.handleIntent(SearchNoteScreenIntent.ClearSearch) },
        onNoteClick = navigateToNoteDetailsScreen,
    )
}