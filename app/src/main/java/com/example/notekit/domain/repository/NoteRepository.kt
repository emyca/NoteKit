package com.example.notekit.domain.repository

import com.example.notekit.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insert(note: Note)

    fun getAll(): Flow<List<Note>>

    fun getById(id: Int): Flow<Note>

    suspend fun update(note: Note)

    suspend fun delete(note: Note)

    suspend fun deleteById(id: Int)
}