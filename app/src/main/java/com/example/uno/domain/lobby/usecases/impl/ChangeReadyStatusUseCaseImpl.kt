package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.usecases.interfaces.ChangeReadyStatusUseCase
import javax.inject.Inject

class ChangeReadyStatusUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : ChangeReadyStatusUseCase {

    override suspend fun changeReadyStatus(playerId: Int) {
        lobbyRepository.changeReadyStatus(playerId)
    }
}