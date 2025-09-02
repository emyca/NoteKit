package com.example.notekit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.notekit.home.presentation.navigation.NOTES_SCREEN_ROUTE
import com.example.notekit.home.presentation.navigation.notesScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NOTES_SCREEN_ROUTE
    ) {
        notesScreen(
            navigateToAddNoteScreen = {},
            navigateToDetailNoteScreen = {}
        )
    }
}