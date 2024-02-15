package com.example.uno.domain.lobby.usecases.interfaces

interface ChangeReadyStatusUseCase {
    suspend fun changeReadyStatus(playerId: Int)
}