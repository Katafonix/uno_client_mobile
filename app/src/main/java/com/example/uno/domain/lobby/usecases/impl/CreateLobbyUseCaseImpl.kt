package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.usecases.interfaces.CreateLobbyUseCase
import javax.inject.Inject

class CreateLobbyUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : CreateLobbyUseCase {
    override suspend fun createLobby(numPlayers: Int): String {
        return lobbyRepository.createLobby(numPlayers)
    }
}