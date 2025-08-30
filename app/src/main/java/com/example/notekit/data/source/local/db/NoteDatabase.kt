package com.example.notekit.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notekit.data.source.local.dao.NoteDao
import com.example.notekit.data.source.local.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}