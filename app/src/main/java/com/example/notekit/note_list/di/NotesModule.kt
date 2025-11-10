package com.example.notekit.note_list.di

import com.example.notekit.note_list.domain.usecase.DeleteUseCase
import com.example.notekit.note_list.domain.usecase.GetAllUseCase
import com.example.notekit.note_list.domain.usecase.impl.DeleteUseCaseImpl
import com.example.notekit.note_list.domain.usecase.impl.GetAllUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesModule {

    @Binds
    abstract fun bindGetAllUseCase(
        getAllUseCase: GetAllUseCaseImpl
    ): GetAllUseCase

    @Binds
    abstract fun bindDeleteUseCase(
        deleteUseCase: DeleteUseCaseImpl
    ): DeleteUseCase
}