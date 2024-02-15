package com.example.uno.domain.joinlobby.usecases.interfaces

interface JoinLobbyUseCase {
    suspend fun joinLobby(code: String)
}