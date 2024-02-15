package com.example.uno.domain.lobby

import com.example.uno.domain.lobby.entities.LobbyPlayer

interface LobbyRepository {
    suspend fun getPlayers(): List<LobbyPlayer>
    suspend fun createLobby(numPlayers: Int): String
    suspend fun joinLobby(code: String)
    suspend fun exitLobby(id: Int)
    suspend fun changeReadyStatus(id: Int)
    suspend fun deletePlayer(id: Int)
    suspend fun getHostId(): Int
    suspend fun getCurrentPlayer(id: Int): LobbyPlayer
}