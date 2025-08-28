package com.example.notekit.domain.usecase

import com.example.notekit.data.source.local.Note
import kotlinx.coroutines.flow.Flow

interface GetByIdUseCase {
    operator fun invoke(id: Int): Flow<Note>
}