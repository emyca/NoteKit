package com.example.notekit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.notekit.home.presentation.navigation.NOTES_SCREEN_ROUTE
import com.example.notekit.home.presentation.navigation.navigateToNotesScreen
import com.example.notekit.home.presentation.navigation.notesScreen
import com.example.notekit.save_note.presentation.navigation.SaveNoteResArg
import com.example.notekit.save_note.presentation.navigation.navigateToSaveNote
import com.example.notekit.save_note.presentation.navigation.saveNoteScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NOTES_SCREEN_ROUTE
    ) {
        notesScreen(
            navigateToInsertNote = {
                navController.navigateToSaveNote(
                    SaveNoteResArg.INSERT_NOTE,null)
            },
            navigateToUpdateNote = {
                navController.navigateToSaveNote(
                    SaveNoteResArg.UPDATE_NOTE,it)
            },
        )

        saveNoteScreen { navController.navigateToNotesScreen() }
    }
}