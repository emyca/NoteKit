package com.example.notekit.note_details.domain.usecase

import com.example.notekit.core.domain.model.Note

interface DeleteUseCase {
    suspend operator fun invoke(note: Note)
}