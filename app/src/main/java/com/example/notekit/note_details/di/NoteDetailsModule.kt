package com.example.notekit.note_details.di

import com.example.notekit.note_details.domain.usecase.DeleteUseCase
import com.example.notekit.note_details.domain.usecase.impl.DeleteUseCaseImpl
import com.example.notekit.note_details.domain.usecase.GetByIdUseCase
import com.example.notekit.note_details.domain.usecase.impl.GetByIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteDetailsModule {

    @Binds
    abstract fun bindGetByIdUseCase(
        getByIdUseCaseImpl: GetByIdUseCaseImpl
    ): GetByIdUseCase

    @Binds
    abstract fun bindDeleteUseCase(
        deleteUseCase: DeleteUseCaseImpl
    ): DeleteUseCase
}