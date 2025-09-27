package com.example.notekit.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.home.domain.usecase.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

internal sealed interface NotesScreenUiState {
    data object Loading : NotesScreenUiState
    data object Empty : NotesScreenUiState
    data class Content(val notes: List<Note>) : NotesScreenUiState
}

@HiltViewModel
internal class NotesViewModel @Inject constructor(
    getAllUseCase: GetAllUseCase
) : ViewModel() {

    private val notes = getAllUseCase()

    val uiState: StateFlow<NotesScreenUiState> =
        notes.map { noteList ->
            if (noteList.isNotEmpty())
                NotesScreenUiState.Content(noteList)
            else NotesScreenUiState.Empty
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NotesScreenUiState.Loading
        )
}