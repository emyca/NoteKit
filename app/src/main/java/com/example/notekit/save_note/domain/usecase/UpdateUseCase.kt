package com.example.notekit.save_note.domain.usecase

import com.example.notekit.core.domain.model.Note

interface UpdateUseCase {
    suspend operator fun invoke(note: Note)
}