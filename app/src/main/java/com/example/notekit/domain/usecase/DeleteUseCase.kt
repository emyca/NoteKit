package com.example.notekit.domain.usecase

import com.example.notekit.domain.model.Note

interface DeleteUseCase {
    suspend operator fun invoke(note: Note)
}