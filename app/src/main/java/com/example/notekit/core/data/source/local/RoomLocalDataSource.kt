package com.example.notekit.data.source.local

import com.example.notekit.core.data.mapper.toNote
import com.example.notekit.core.data.mapper.toNoteEntity
import com.example.notekit.data.source.local.dao.NoteDao
import com.example.notekit.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val noteDao: NoteDao
) : LocalDataSource {

    override suspend fun insert(note: Note) {
        noteDao.insert(note.toNoteEntity())
    }

    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll().map { noteEntityList ->
            noteEntityList.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override fun getById(id: Int): Flow<Note> {
        return noteDao.getById(id).map { noteEntity ->
            noteEntity.toNote()
        }
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note.toNoteEntity())
    }

    override suspend fun update(note: Note) {
        noteDao.update(note.toNoteEntity())
    }
}