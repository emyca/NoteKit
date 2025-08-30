package com.example.notekit.data.source.local

import com.example.notekit.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insert(note: Note)

    fun getAll(): Flow<List<Note>>

    fun getById(id: Int): Flow<Note>

    suspend fun update(note: Note)

    suspend fun delete(note: Note)
}