package com.example.notekit.home.presentation

import androidx.lifecycle.ViewModel
import com.example.notekit.core.domain.model.Note
import com.example.notekit.home.domain.usecase.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

internal sealed interface NotesScreenUiState {
    data object Empty: NotesScreenUiState
    data class Content(val notes: List<Note>): NotesScreenUiState
}

@HiltViewModel
internal class NotesViewModel @Inject constructor(
    getAllUseCase: GetAllUseCase
) : ViewModel() {}