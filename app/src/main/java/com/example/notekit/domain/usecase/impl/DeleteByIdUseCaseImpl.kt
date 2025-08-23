package com.example.notekit.domain.usecase.impl

import com.example.notekit.domain.repository.NoteRepository
import com.example.notekit.domain.usecase.DeleteByIdUseCase
import javax.inject.Inject

class DeleteByIdUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : DeleteByIdUseCase {
    override suspend fun invoke(id: Int) =
        noteRepository.deleteById(id)
}