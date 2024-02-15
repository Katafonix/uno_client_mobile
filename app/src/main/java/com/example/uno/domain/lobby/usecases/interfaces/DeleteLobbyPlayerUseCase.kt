package com.example.uno.domain.lobby.usecases.interfaces

interface DeleteLobbyPlayerUseCase {
    suspend fun deletePlayer(id: Int)
}