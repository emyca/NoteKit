package com.example.notekit.domain.usecase

import com.example.notekit.domain.model.Note

interface InsertUseCase {
    suspend operator fun invoke(note: Note)
}