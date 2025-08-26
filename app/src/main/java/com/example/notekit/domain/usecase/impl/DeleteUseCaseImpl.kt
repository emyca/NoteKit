package com.example.notekit.domain.usecase.impl

import com.example.notekit.domain.model.Note
import com.example.notekit.domain.repository.NoteRepository
import com.example.notekit.domain.usecase.DeleteUseCase
import javax.inject.Inject

class DeleteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : DeleteUseCase {
    override suspend fun invoke(note: Note) =
        noteRepository.delete(note)
}