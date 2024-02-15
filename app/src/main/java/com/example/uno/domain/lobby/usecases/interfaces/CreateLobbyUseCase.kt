package com.example.uno.domain.lobby.usecases.interfaces

interface CreateLobbyUseCase {
    suspend fun createLobby(numPlayers: Int): String
}