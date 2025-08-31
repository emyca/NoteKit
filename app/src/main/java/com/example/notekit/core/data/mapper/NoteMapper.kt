package com.example.notekit.core.data.mapper

import com.example.notekit.core.data.source.local.model.NoteEntity
import com.example.notekit.core.domain.model.Note

fun NoteEntity.toNote() = Note(id, name, content)

fun Note.toNoteEntity() = NoteEntity(id, name, content)