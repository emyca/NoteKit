package com.example.notekit.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.example.notekit.core.data.repository.NoteRepositoryImpl
import com.example.notekit.core.data.source.local.LocalDataSource
import com.example.notekit.core.data.source.local.RoomLocalDataSource
import com.example.notekit.core.data.source.local.db.NoteDatabase
import com.example.notekit.core.domain.repository.NoteRepository
import com.example.notekit.core.utils.SupportFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.UUID

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModuleProvider {

    @Provides
    fun provideNoteDao(database: NoteDatabase) = database.noteDao()

    // It has no encryption
//    @Provides
//    @Singleton
//    fun provideLocalDatabase(
//        @ApplicationContext context: Context
//    ) = Room.databaseBuilder(
//        context,
//        NoteDatabase::class.java,
//        "note-database"
//    ).build()

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        System.loadLibrary("sqlcipher")
        val passphrase =  UUID.randomUUID().toString().toByteArray()
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note-database"
        ).openHelperFactory(factory)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModuleBinder {

    @Binds
    abstract fun bindRoomLocalDataSource(
        roomLocalDataSource: RoomLocalDataSource
    ): LocalDataSource

    @Binds
    abstract fun bindNoteRepository(
        noteRepository: NoteRepositoryImpl
    ): NoteRepository
}