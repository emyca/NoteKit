package com.example.notekit.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notekit.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}