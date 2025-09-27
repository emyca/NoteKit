package com.example.notekit.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.home.domain.usecase.DeleteUseCase
import com.example.notekit.home.domain.usecase.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface NotesScreenUiState {
    data object Loading : NotesScreenUiState
    data object Empty : NotesScreenUiState
    data class Content(val notes: List<Note>) : NotesScreenUiState
}

internal sealed interface NotesScreenUiEvent {
    data class OnDeleteClick(val note: Note) : NotesScreenUiEvent
}

@HiltViewModel
internal class NotesViewModel @Inject constructor(
    private val deleteUseCase: DeleteUseCase,
    getAllUseCase: GetAllUseCase
) : ViewModel() {

    fun handleEvent(event: NotesScreenUiEvent) {
        when (event) {
            is NotesScreenUiEvent.OnDeleteClick ->
                delete(event.note)
        }
    }

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

    private fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase(note)
        }
    }
}