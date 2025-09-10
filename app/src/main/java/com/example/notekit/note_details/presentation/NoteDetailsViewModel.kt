package com.example.notekit.note_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.note_details.domain.usecase.DeleteUseCase
import com.example.notekit.note_details.domain.usecase.GetByIdUseCase
import com.example.notekit.note_details.presentation.navigation.NOTE_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface NoteDetailsUiEvent {
    data class OnDeleteClick(val note: Note) : NoteDetailsUiEvent
}

sealed interface NoteDetailsUiState {
    data class NoteState(
        val id: Int = 0,
        val name: String = "",
        val content: String = ""
    ) : NoteDetailsUiState
}

@HiltViewModel
internal class NoteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getByIdUseCase: GetByIdUseCase,
    private val deleteUseCase: DeleteUseCase,
) : ViewModel() {

    private val noteId: String? = savedStateHandle[NOTE_ID_ARG]

    init {
        if (noteId != null) loadNote(noteId = noteId.toInt())
    }

    private val _uiState = MutableStateFlow<NoteDetailsUiState>(
        NoteDetailsUiState.NoteState()
    )

    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: NoteDetailsUiEvent) {
        when (event) {
            is NoteDetailsUiEvent.OnDeleteClick ->
                deleteNote(event.note)
        }
    }

    private fun loadNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = getByIdUseCase(noteId).first()
            _uiState.update {
                NoteDetailsUiState.NoteState(
                    id = noteId,
                    name = note.name,
                    content = note.content
                )
            }
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase(note)
        }
    }
}