package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.usecases.interfaces.DeleteLobbyPlayerUseCase
import javax.inject.Inject

class DeleteLobbyPlayerUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : DeleteLobbyPlayerUseCase {
    override suspend fun deletePlayer(id: Int) {
        lobbyRepository.deletePlayer(id)
    }
}