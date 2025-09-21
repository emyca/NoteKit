package com.example.notekit.note_details.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notekit.note_details.presentation.NoteDetailsRoute

const val NOTE_DETAILS_ROUTE = "note_details_route"
const val NOTE_ID_ARG = "note_id"

fun NavController.navigateToNoteDetailsScreen(
    noteId: String?
) {
    navigate(
        route = "$NOTE_DETAILS_ROUTE/$noteId",
    ) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.noteDetailsScreen(
    navigateToNotesScreen: () -> Unit,
    navigateToSaveNoteScreen: (String) -> Unit
) {
    composable(
        route = "$NOTE_DETAILS_ROUTE/{${NOTE_ID_ARG}}",
        arguments = listOf(
            navArgument(NOTE_ID_ARG) { type = NavType.StringType; nullable = true },
        )
    ) { backStackEntry ->
        NoteDetailsRoute(
            navigateToNotesScreen = navigateToNotesScreen,
            navigateToSaveNoteScreen = navigateToSaveNoteScreen
        )
    }
}