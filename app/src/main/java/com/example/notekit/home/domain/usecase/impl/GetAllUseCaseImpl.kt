package com.example.notekit.home.domain.usecase.impl

import com.example.notekit.core.domain.repository.NoteRepository
import com.example.notekit.home.domain.usecase.GetAllUseCase
import javax.inject.Inject

class GetAllUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : GetAllUseCase {
    override operator fun invoke() =
        noteRepository.getAll()
}