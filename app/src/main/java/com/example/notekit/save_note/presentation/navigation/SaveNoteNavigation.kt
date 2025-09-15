package com.example.notekit.save_note.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notekit.R
import com.example.notekit.save_note.presentation.SaveNoteRoute

const val SAVE_NOTE_ROUTE = "save_note_route"
const val NOTE_ID_ARG = "note_id"
const val TOP_BAR_TITLE_ARG = "top_bar_title"

enum class SaveNoteResArg {
    INSERT_NOTE, UPDATE_NOTE
}

fun NavController.navigateToSaveNote(
    topBarTitleResArg: SaveNoteResArg,
    noteId: String?
) {
    val topBarTitleResId = when (topBarTitleResArg) {
        SaveNoteResArg.INSERT_NOTE -> R.string.insert_top_bar_title
        SaveNoteResArg.UPDATE_NOTE -> R.string.update_top_bar_title
    }
    navigate(
        route = "$SAVE_NOTE_ROUTE/$topBarTitleResId/$noteId",
    ) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.saveNoteScreen(
    navigateToNoteDetailsScreen: (String) -> Unit,
    navigateToNotesScreen: () -> Unit) {
    composable(
        route = "$SAVE_NOTE_ROUTE/{$TOP_BAR_TITLE_ARG}/{$NOTE_ID_ARG}",
        arguments = listOf(
            navArgument(NOTE_ID_ARG) { type = NavType.StringType; nullable = true },
            navArgument(TOP_BAR_TITLE_ARG) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        SaveNoteRoute(
            topBarTitle = backStackEntry.arguments?.getInt(TOP_BAR_TITLE_ARG),
            navigateToNoteDetailsScreen = navigateToNoteDetailsScreen,
            navigateToNotesScreen = navigateToNotesScreen
        )
    }
}