package com.example.notekit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.notekit.navigation.AppNavigation
import com.example.notekit.ui.theme.NoteKitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteKitTheme(dynamicColor = false) {
                AppNavigation()
            }
        }
    }
}
