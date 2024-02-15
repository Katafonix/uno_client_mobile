package com.example.uno.data.lobby

import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.lobby.entities.LobbyPlayer
import javax.inject.Inject

class LobbyRepositoryImpl @Inject constructor(
    private val lobbyDataSource: LobbyDataSource
) : LobbyRepository {
    override suspend fun getPlayers(): List<LobbyPlayer> = lobbyDataSource.getPlayers()

    override suspend fun createLobby(numPlayers: Int): String {
        return lobbyDataSource.createLobby(numPlayers)
    }

    override suspend fun joinLobby(code: String) {
        lobbyDataSource.joinLobby(code)
    }

    override suspend fun exitLobby(id: Int) {
        lobbyDataSource.exitLobby(id)
    }

    override suspend fun changeReadyStatus(id: Int) {
        lobbyDataSource.changeStatus(id)
    }

    override suspend fun deletePlayer(id: Int) {
        lobbyDataSource.deletePlayer(id)
    }

    override suspend fun getHostId(): Int {
        return lobbyDataSource.getHostId()
    }

    override suspend fun getCurrentPlayer(id: Int): LobbyPlayer {
        return lobbyDataSource.getCurrentPlayer(id)
    }
}