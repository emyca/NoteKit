package com.example.notekit.search_note.domain.usecase

import com.example.notekit.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface GetNoteByNameUseCase {
    operator fun invoke(search: String?): Flow<List<Note>>
}