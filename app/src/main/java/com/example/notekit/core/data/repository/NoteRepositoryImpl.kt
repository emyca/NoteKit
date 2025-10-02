package com.example.notekit.core.data.repository

import com.example.notekit.core.data.source.local.LocalDataSource
import com.example.notekit.core.domain.model.Note
import com.example.notekit.core.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : NoteRepository {

    override suspend fun insert(note: Note) =
        localDataSource.insert(note)

    override fun getAll(): Flow<List<Note>> =
        localDataSource.getAll()

    override fun getById(id: Int): Flow<Note> =
        localDataSource.getById(id)

    override suspend fun update(note: Note) =
        localDataSource.update(note)

    override suspend fun delete(note: Note) =
        localDataSource.delete(note)

    override fun getByName(search: String?): Flow<List<Note>> =
        localDataSource.getByName(search)
}