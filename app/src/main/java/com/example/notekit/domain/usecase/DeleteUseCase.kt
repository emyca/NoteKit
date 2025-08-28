package com.example.notekit.domain.usecase

import com.example.notekit.data.source.local.Note

interface DeleteUseCase {
    suspend operator fun invoke(note: Note)
}