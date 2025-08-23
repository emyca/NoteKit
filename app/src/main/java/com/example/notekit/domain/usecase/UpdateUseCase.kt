package com.example.notekit.domain.usecase

import com.example.notekit.data.source.local.Note

interface UpdateUseCase {
    suspend operator fun invoke(note: Note)
}