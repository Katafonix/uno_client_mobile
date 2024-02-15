package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.entities.LobbyPlayer
import com.example.uno.domain.lobby.usecases.interfaces.GetLobbyPlayersUseCase
import javax.inject.Inject

class GetLobbyPlayersUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : GetLobbyPlayersUseCase {
    override suspend fun getPlayers(): List<LobbyPlayer> {
        return lobbyRepository.getPlayers()
    }
}