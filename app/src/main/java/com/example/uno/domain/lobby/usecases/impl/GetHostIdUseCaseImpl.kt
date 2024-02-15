package com.example.uno.domain.lobby.usecases.impl

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.usecases.interfaces.GetHostIdUseCase
import javax.inject.Inject

class GetHostIdUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : GetHostIdUseCase {
    override suspend fun getHostId(): Int {
        return lobbyRepository.getHostId()
    }
}