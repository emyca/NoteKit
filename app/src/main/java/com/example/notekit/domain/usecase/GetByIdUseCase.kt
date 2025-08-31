package com.example.notekit.domain.usecase

import com.example.notekit.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface GetByIdUseCase {
    operator fun invoke(id: Int): Flow<Note>
}