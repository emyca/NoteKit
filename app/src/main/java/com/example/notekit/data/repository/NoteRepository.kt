package com.example.notekit.data.repository

import com.example.notekit.data.source.local.Note
import com.example.notekit.data.source.local.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insert(note: Note) = noteDao.insert(note)

    fun getAll(): Flow<List<Note>> = noteDao.getAll()

    fun getById(id: Int): Flow<Note> = noteDao.getById(id)

    suspend fun update(note: Note) = noteDao.update(note)

    suspend fun delete(note: Note) = noteDao.delete(note)

    suspend fun deleteById(id: Int) = noteDao.deleteById(id)
}