package com.example.notekit.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.home.domain.usecase.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
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

    var searchQuery by mutableStateOf("")
        private set

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

    val searchResults: StateFlow<List<Note>> =
        snapshotFlow { searchQuery }
            .combine(notes) { searchQuery, noteList ->
                when {
                    searchQuery.isNotEmpty() -> noteList.filter { note ->
                        note.name.contains(searchQuery, ignoreCase = true)
                    }

                    else -> noteList
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList(),
            )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }
}