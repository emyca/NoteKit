package com.example.notekit.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.notekit.home.presentation.NotesScreenRoute

const val NOTES_SCREEN_ROUTE = "notes_screen"

fun NavController.navigateToNotesScreen() = navigate(NOTES_SCREEN_ROUTE) {
    popUpTo(NOTES_SCREEN_ROUTE) {
        inclusive = true
    }
}

fun NavGraphBuilder.notesScreen(
    navigateToInsertNote: () -> Unit,
    navigateToNoteDetails: (String) -> Unit,
) {
    composable(route = NOTES_SCREEN_ROUTE) {
        NotesScreenRoute(
            navigateToInsertNote = navigateToInsertNote,
            navigateToNoteDetails = navigateToNoteDetails
        )
    }
}