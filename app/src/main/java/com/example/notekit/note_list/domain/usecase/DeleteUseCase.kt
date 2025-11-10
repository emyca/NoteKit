package com.example.notekit.note_list.domain.usecase

import com.example.notekit.core.domain.model.Note

interface DeleteUseCase {
    suspend operator fun invoke(note: Note)
}