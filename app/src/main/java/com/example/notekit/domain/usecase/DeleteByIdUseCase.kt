package com.example.notekit.domain.usecase

interface DeleteByIdUseCase {
    suspend operator fun invoke(id: Int)
}