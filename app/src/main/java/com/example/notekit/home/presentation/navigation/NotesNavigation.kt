package com.example.notekit.home.presentation.navigation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.notekit.home.presentation.NotesRoute

const val NOTES_ROUTE = "notes_route"

fun NavController.navigateToNotesScreen() = navigate(NOTES_ROUTE) {
    popUpTo(NOTES_ROUTE) {
        inclusive = true
    }
}

fun NavGraphBuilder.notesScreen(
    navigateToSaveNoteScreen: () -> Unit,
    navigateToNoteDetailsScreen: (String) -> Unit,
) {
    composable(route = NOTES_ROUTE) {
        NotesRoute(
            navigateToSaveNoteScreen = navigateToSaveNoteScreen,
            navigateToNoteDetailsScreen = navigateToNoteDetailsScreen,
        )
    }
}