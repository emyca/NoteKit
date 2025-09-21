package com.example.notekit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.notekit.home.presentation.navigation.NOTES_ROUTE
import com.example.notekit.home.presentation.navigation.navigateToNotesScreen
import com.example.notekit.home.presentation.navigation.notesScreen
import com.example.notekit.note_details.presentation.navigation.navigateToNoteDetailsScreen
import com.example.notekit.note_details.presentation.navigation.noteDetailsScreen
import com.example.notekit.save_note.presentation.navigation.SaveNoteResArg
import com.example.notekit.save_note.presentation.navigation.navigateToSaveNoteScreen
import com.example.notekit.save_note.presentation.navigation.saveNoteScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NOTES_ROUTE
    ) {
        notesScreen(
            navigateToSaveNoteScreen = {
                navController.navigateToSaveNoteScreen(
                    SaveNoteResArg.INSERT_NOTE, null
                )
            },
            navigateToNoteDetailsScreen = {
                navController.navigateToNoteDetailsScreen(it)
            },
        )

        noteDetailsScreen(
            navigateToNotesScreen = { navController.navigateToNotesScreen() },
            navigateToSaveNoteScreen = {
                navController.navigateToSaveNoteScreen(
                    SaveNoteResArg.UPDATE_NOTE, it
                )
            }
        )

        saveNoteScreen(
            navigateToNoteDetailsScreen = { navController.navigateToNoteDetailsScreen(it) },
            navigateToNotesScreen = { navController.navigateToNotesScreen() },
        )
    }
}