package com.example.notekit.search_note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notekit.core.domain.model.Note
import com.example.notekit.search_note.domain.usecase.GetNoteByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface SearchNoteScreenState {
    object Idle : SearchNoteScreenState
    object Loading : SearchNoteScreenState
    data class Success(val results: List<Note>) : SearchNoteScreenState
    data class Error(val message: String) : SearchNoteScreenState
}

sealed class SearchNoteScreenIntent {
    data class UpdateQuery(val query: String) : SearchNoteScreenIntent()
    data class Search(val query: String) : SearchNoteScreenIntent()
    data object ClearSearch : SearchNoteScreenIntent()
}

@OptIn(FlowPreview::class)
@HiltViewModel
internal class SearchNoteViewModel @Inject constructor(
    private val getNoteByNameUseCase: GetNoteByNameUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SearchNoteScreenState>(SearchNoteScreenState.Idle)
    val state: StateFlow<SearchNoteScreenState> = _state.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    init {
        // Debounce search requests
        viewModelScope.launch {
            _query.debounce(300L)
                .filter { it.isNotBlank() }
                .collect { performSearch(it) }
        }
    }

    fun handleIntent(intent: SearchNoteScreenIntent) {
        when (intent) {
            is SearchNoteScreenIntent.UpdateQuery -> _query.value = intent.query
            is SearchNoteScreenIntent.Search -> performSearch(intent.query)
            is SearchNoteScreenIntent.ClearSearch -> _state.value = SearchNoteScreenState.Idle
        }
    }

    private fun performSearch(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = SearchNoteScreenState.Loading
            try {
                val resultsFlow = getNoteByNameUseCase(searchQuery)
                resultsFlow.map { noteList ->
                    if (noteList.isNotEmpty())
                        _state.value = SearchNoteScreenState.Success(noteList)
                    else SearchNoteScreenState.Idle
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = SearchNoteScreenState.Loading
                )
            } catch (e: Exception) {
                _state.value = SearchNoteScreenState.Error("Failed to fetch results.")
            }
        }
    }
}

