package com.example.notekit.search_note.domain.usecase.impl

import com.example.notekit.core.domain.repository.NoteRepository
import com.example.notekit.search_note.domain.usecase.GetNoteByNameUseCase
import javax.inject.Inject

class GetNoteByNameUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : GetNoteByNameUseCase {
    override operator fun invoke(search: String?) =
        noteRepository.getByName(search)
}