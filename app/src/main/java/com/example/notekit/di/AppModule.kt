package com.example.notekit.di

import android.content.Context
import androidx.room.Room
import com.example.notekit.core.data.source.local.dao.NoteDao
import com.example.notekit.core.data.source.local.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()
}