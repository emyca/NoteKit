package com.example.notekit.data.mapper

import com.example.notekit.data.source.local.model.NoteEntity
import com.example.notekit.domain.model.Note

fun NoteEntity.toNote() = Note(id, name, content)

fun Note.toNoteEntity() = NoteEntity(id, name, content)