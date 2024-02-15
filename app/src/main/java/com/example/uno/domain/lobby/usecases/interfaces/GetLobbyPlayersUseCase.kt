package com.example.uno.domain.lobby.usecases.interfaces

import com.example.uno.domain.lobby.entities.LobbyPlayer

interface GetLobbyPlayersUseCase {
    suspend fun getPlayers(): List<LobbyPlayer>
}