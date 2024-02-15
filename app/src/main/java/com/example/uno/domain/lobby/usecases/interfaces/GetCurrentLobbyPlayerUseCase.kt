package com.example.uno.domain.lobby.usecases.interfaces

import com.example.uno.domain.lobby.entities.LobbyPlayer

interface GetCurrentLobbyPlayerUseCase {
    suspend fun getCurrentPlayer(playerId: Int): LobbyPlayer
}