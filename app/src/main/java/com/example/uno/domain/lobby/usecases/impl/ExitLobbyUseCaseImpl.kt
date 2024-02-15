package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.usecases.interfaces.ExitLobbyUseCase
import javax.inject.Inject

class ExitLobbyUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : ExitLobbyUseCase {
    override suspend fun exitLobby(playerId: Int) {
        lobbyRepository.exitLobby(playerId)
    }
}