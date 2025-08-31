package com.example.notekit.core.data.source.local

import com.example.notekit.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insert(note: Note)

    fun getAll(): Flow<List<Note>>

    fun getById(id: Int): Flow<Note>

    suspend fun update(note: Note)

    suspend fun delete(note: Note)
}