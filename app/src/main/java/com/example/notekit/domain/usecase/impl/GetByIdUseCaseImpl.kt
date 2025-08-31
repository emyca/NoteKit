package com.example.notekit.domain.usecase.impl

import com.example.notekit.core.domain.repository.NoteRepository
import com.example.notekit.domain.usecase.GetByIdUseCase
import javax.inject.Inject

class GetByIdUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : GetByIdUseCase {
    override operator fun invoke(id: Int) =
        noteRepository.getById(id)
}