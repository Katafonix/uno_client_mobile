package com.example.uno.domain.lobby.usecases.interfaces

interface ExitLobbyUseCase {
    suspend fun exitLobby(playerId: Int)
}