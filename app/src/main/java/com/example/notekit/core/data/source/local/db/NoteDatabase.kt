package com.example.notekit.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notekit.core.data.source.local.dao.NoteDao
import com.example.notekit.core.data.source.local.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}