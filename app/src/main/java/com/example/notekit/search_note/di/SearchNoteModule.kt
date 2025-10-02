package com.example.notekit.search_note.di

import com.example.notekit.search_note.domain.usecase.GetNoteByNameUseCase
import com.example.notekit.search_note.domain.usecase.impl.GetNoteByNameUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchNoteModule {

    @Binds
    abstract fun bindGetNoteByNameUseCase(
        getNoteByNameUseCase: GetNoteByNameUseCaseImpl
    ): GetNoteByNameUseCase
}