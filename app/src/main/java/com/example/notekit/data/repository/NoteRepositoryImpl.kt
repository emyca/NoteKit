package com.example.notekit.data.repository

import com.example.notekit.data.source.local.Note
import com.example.notekit.data.source.local.NoteDao
import com.example.notekit.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {

    override suspend fun insert(note: Note) =
        noteDao.insert(note)

    override fun getAll(): Flow<List<Note>> =
        noteDao.getAll()

    override fun getById(id: Int): Flow<Note> =
        noteDao.getById(id)

    override suspend fun update(note: Note) =
        noteDao.update(note)

    override suspend fun delete(note: Note) =
        noteDao.delete(note)

    override suspend fun deleteById(id: Int) =
        noteDao.deleteById(id)
}