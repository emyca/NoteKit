package com.example.notekit.save_note.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.save_note.domain.usecase.GetByIdUseCase
import com.example.notekit.save_note.domain.usecase.InsertUseCase
import com.example.notekit.save_note.domain.usecase.UpdateUseCase
import com.example.notekit.save_note.presentation.navigation.NOTE_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface SaveNoteUiEvent {
    data class OnNoteNameChanged(val name: String) : SaveNoteUiEvent
    data class OnNoteContentChanged(val content: String) : SaveNoteUiEvent
    data object OnSaveClicked : SaveNoteUiEvent
}

sealed interface SaveNoteUiState {
    data class NoteState(
        val id: Int = 0,
        val name: String = "",
        val content: String = ""
    ) : SaveNoteUiState
}

@HiltViewModel
internal class SaveNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertUseCase: InsertUseCase,
    private val updateUseCase: UpdateUseCase,
    private val getByIdUseCase: GetByIdUseCase
) : ViewModel() {

    private val noteId: String? = savedStateHandle[NOTE_ID_ARG]

    init {
        if (noteId != null) loadNote(noteId = noteId.toInt())
    }

    private val _uiState = MutableStateFlow<SaveNoteUiState>(
        SaveNoteUiState.NoteState()
    )
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: SaveNoteUiEvent) {
        when (event) {
            is SaveNoteUiEvent.OnNoteNameChanged -> setName(event.name)
            is SaveNoteUiEvent.OnNoteContentChanged -> setContent(event.content)
            is SaveNoteUiEvent.OnSaveClicked -> addOrUpdateNote(noteId)
        }
    }

    private fun loadNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = getByIdUseCase(noteId).first()
            _uiState.update {
                SaveNoteUiState.NoteState(
                    id = noteId,
                    name = note.name,
                    content = note.content
                )
            }
        }
    }

    private fun setName(name: String) {
        _uiState.update {
            (it as SaveNoteUiState.NoteState).copy(name = name)
        }
    }

    private fun setContent(content: String) {
        _uiState.update {
            (it as SaveNoteUiState.NoteState).copy(content = content)
        }
    }

    private fun addOrUpdateNote(noteId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val state = _uiState.value as SaveNoteUiState.NoteState
            val note = Note(
                id = state.id,
                name = state.name,
                content = state.content
            )

            if (noteId != null) {
                updateUseCase(note)
            } else insertUseCase(note)
        }
    }
}