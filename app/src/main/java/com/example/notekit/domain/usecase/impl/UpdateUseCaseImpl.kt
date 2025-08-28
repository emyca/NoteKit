package com.example.notekit.domain.usecase.impl

import com.example.notekit.data.source.local.Note
import com.example.notekit.domain.repository.NoteRepository
import com.example.notekit.domain.usecase.UpdateUseCase
import javax.inject.Inject

class UpdateUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : UpdateUseCase {
    override suspend fun invoke(note: Note) =
        noteRepository.update(note)
}