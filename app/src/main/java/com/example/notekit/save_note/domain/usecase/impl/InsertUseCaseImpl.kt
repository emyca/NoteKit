package com.example.notekit.save_note.domain.usecase.impl

import com.example.notekit.core.domain.model.Note
import com.example.notekit.core.domain.repository.NoteRepository
import com.example.notekit.save_note.domain.usecase.InsertUseCase
import javax.inject.Inject

class InsertUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : InsertUseCase {
    override suspend operator fun invoke(note: Note) {
        noteRepository.insert(note)
    }
}