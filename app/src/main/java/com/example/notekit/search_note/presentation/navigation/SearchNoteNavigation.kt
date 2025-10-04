package com.example.notekit.search_note.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.notekit.search_note.presentation.SearchNoteRoute

const val SEARCH_NOTE_ROUTE = "search_note_route"

fun NavController.navigateToSearchNoteScreen() = navigate(SEARCH_NOTE_ROUTE) {
    popUpTo(SEARCH_NOTE_ROUTE) {
        inclusive = true
    }
}

fun NavGraphBuilder.searchNoteScreen(
    navigateToNotesScreen: () -> Unit,
    navigateToNoteDetailsScreen: (String) -> Unit,
) {
    composable(route = SEARCH_NOTE_ROUTE) {
        SearchNoteRoute(
            navigateToNotesScreen = navigateToNotesScreen,
            navigateToNoteDetailsScreen = navigateToNoteDetailsScreen,
        )
    }
}