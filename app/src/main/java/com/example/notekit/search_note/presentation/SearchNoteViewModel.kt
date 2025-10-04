package com.example.notekit.search_note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.search_note.domain.usecase.GetNoteByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface SearchNoteScreenState {
    data object Loading : SearchNoteScreenState
    data class Success(val results: List<Note>) : SearchNoteScreenState
    data class Error(val message: String) : SearchNoteScreenState
    data object Idle : SearchNoteScreenState // Initial or empty state
}

sealed class SearchNoteScreenIntent {
    data class PerformSearch(val query: String) : SearchNoteScreenIntent()
    data object ClearSearch : SearchNoteScreenIntent()
}

@HiltViewModel
internal class SearchNoteViewModel @Inject constructor(
    private val getNoteByNameUseCase: GetNoteByNameUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SearchNoteScreenState>(SearchNoteScreenState.Idle)
    val state: StateFlow<SearchNoteScreenState> = _state.asStateFlow()

    fun handleIntent(intent: SearchNoteScreenIntent) {
        when (intent) {
            is SearchNoteScreenIntent.PerformSearch -> performSearch(intent.query)
            SearchNoteScreenIntent.ClearSearch -> _state.value = SearchNoteScreenState.Idle
        }
    }

    private fun performSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isBlank()) {
                _state.value = SearchNoteScreenState.Idle
                return@launch
            }

            _state.value = SearchNoteScreenState.Loading

            try {
                delay(300) // Debounce delay
                val resultsFlow = getNoteByNameUseCase(query)
                val resultsList = mutableListOf<Note>()
                resultsFlow.collect {
                    it.forEach { note ->
                        resultsList.add(note)
                    }
                    _state.value = SearchNoteScreenState.Success(resultsList)
                }
            } catch (e: Exception) {
                _state.value = SearchNoteScreenState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

