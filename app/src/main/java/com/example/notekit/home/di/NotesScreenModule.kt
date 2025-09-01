package com.example.notekit.home.di

import com.example.notekit.home.domain.usecase.DeleteUseCase
import com.example.notekit.home.domain.usecase.GetAllUseCase
import com.example.notekit.home.domain.usecase.impl.DeleteUseCaseImpl
import com.example.notekit.home.domain.usecase.impl.GetAllUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesScreenModule {

    @Binds
    abstract fun bindGetAllUseCase(
        getAllUseCase: GetAllUseCaseImpl
    ): GetAllUseCase

    @Binds
    abstract fun bindDeleteUseCase(
        deleteUseCase: DeleteUseCaseImpl
    ): DeleteUseCase
}