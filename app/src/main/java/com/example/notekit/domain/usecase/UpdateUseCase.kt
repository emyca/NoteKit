package com.example.notekit.domain.usecase

import com.example.notekit.domain.model.Note

interface UpdateUseCase {
    suspend operator fun invoke(note: Note)
}