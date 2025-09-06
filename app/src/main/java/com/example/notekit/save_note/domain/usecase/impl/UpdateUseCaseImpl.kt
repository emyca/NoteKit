package com.example.notekit.save_note.domain.usecase.impl

import com.example.notekit.core.domain.model.Note
import com.example.notekit.core.domain.repository.NoteRepository
import com.example.notekit.save_note.domain.usecase.UpdateUseCase
import javax.inject.Inject

class UpdateUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : UpdateUseCase {
    override suspend fun invoke(note: Note) =
        noteRepository.update(note)
}