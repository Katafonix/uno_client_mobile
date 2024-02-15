package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.entities.LobbyPlayer
import com.example.uno.domain.lobby.usecases.interfaces.GetCurrentLobbyPlayerUseCase
import javax.inject.Inject

class GetCurrentLobbyPlayerUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : GetCurrentLobbyPlayerUseCase {
    override suspend fun getCurrentPlayer(playerId: Int): LobbyPlayer {
        return lobbyRepository.getCurrentPlayer(playerId)
    }
}