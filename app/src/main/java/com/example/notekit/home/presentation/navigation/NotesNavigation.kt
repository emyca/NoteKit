package com.example.notekit.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.notekit.home.presentation.NotesRoute

const val NOTES_ROUTE = "notes_screen"

fun NavController.navigateToNotesScreen() = navigate(NOTES_ROUTE) {
    popUpTo(NOTES_ROUTE) {
        inclusive = true
    }
}

fun NavGraphBuilder.notesScreen(
    navigateToInsertNote: () -> Unit,
    navigateToNoteDetails: (String) -> Unit,
) {
    composable(route = NOTES_ROUTE) {
        NotesRoute(
            navigateToInsertNote = navigateToInsertNote,
            navigateToNoteDetails = navigateToNoteDetails
        )
    }
}