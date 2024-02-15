package com.example.uno.domain.joinlobby.usecases.impl

import com.example.uno.core.common.EmptyCodeException
import com.example.uno.domain.joinlobby.usecases.interfaces.JoinLobbyUseCase
import com.example.uno.domain.lobby.LobbyRepository
import javax.inject.Inject

class JoinLobbyUseCaseImpl @Inject constructor(
    private val lobbyRepository: LobbyRepository
) : JoinLobbyUseCase {
    override suspend fun joinLobby(code: String) {
        if (code.isBlank()) throw EmptyCodeException()
        lobbyRepository.joinLobby(code)
    }
}