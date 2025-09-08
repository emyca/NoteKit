package com.example.notekit.save_note.di

import com.example.notekit.save_note.domain.usecase.GetByIdUseCase
import com.example.notekit.save_note.domain.usecase.InsertUseCase
import com.example.notekit.save_note.domain.usecase.UpdateUseCase
import com.example.notekit.save_note.domain.usecase.impl.GetByIdUseCaseImpl
import com.example.notekit.save_note.domain.usecase.impl.InsertUseCaseImpl
import com.example.notekit.save_note.domain.usecase.impl.UpdateUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveNoteModule {

    @Binds
    abstract fun bindInsertUseCase(
        insertUseCaseImpl: InsertUseCaseImpl
    ): InsertUseCase

    @Binds
    abstract fun bindGetByIdUseCase(
        getByIdUseCaseImpl: GetByIdUseCaseImpl
    ): GetByIdUseCase

    @Binds
    abstract fun bindUpdateUseCase(
        updateUseCaseImpl: UpdateUseCaseImpl
    ): UpdateUseCase
}