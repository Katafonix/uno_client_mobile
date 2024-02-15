package com.example.uno.data.lobby

import com.example.uno.domain.lobby.entities.LobbyPlayer

interface LobbyDataSource {

    suspend fun getPlayers(): List<LobbyPlayer>

    suspend fun createLobby(numPlayers: Int): String

    suspend fun joinLobby(code: String)

    suspend fun exitLobby(id: Int)

    suspend fun changeStatus(id: Int)

    suspend fun deletePlayer(id: Int)

    suspend fun getCurrentPlayer(id: Int): LobbyPlayer

    suspend fun getHostId(): Int
}