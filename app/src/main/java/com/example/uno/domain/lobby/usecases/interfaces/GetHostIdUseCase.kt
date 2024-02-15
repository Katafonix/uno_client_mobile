package com.example.uno.domain.lobby.usecases.interfaces

interface GetHostIdUseCase {
    suspend fun getHostId(): Int
}