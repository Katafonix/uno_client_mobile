package com.example.uno.data.lobby

import com.example.uno.core.common.BackendException
import com.example.uno.domain.lobby.entities.LobbyPlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeLobbyDataSource @Inject constructor() : LobbyDataSource {

    private val playerList: MutableList<LobbyPlayer> = mutableListOf(
        LobbyPlayer(id = 1, nickname = "player1", readyStatus = false),
        LobbyPlayer(id = 2, nickname = "player2", readyStatus = false),
        LobbyPlayer(id = 3, nickname = "player3", readyStatus = false),
        LobbyPlayer(id = 4, nickname = "player4", readyStatus = false)
    )

    override suspend fun getPlayers(): List<LobbyPlayer> {
        if (SUCCESS_RESPONSE) {
            return playerList
        } else throw BackendException(400, "Ошибка")
    }

    override suspend fun createLobby(numPlayers: Int): String {
        if (SUCCESS_RESPONSE) return "Lobby"
        else throw BackendException(400, "Ошибка")
    }

    override suspend fun joinLobby(code: String) {
        if (code != LOBBY_CODE) throw BackendException(400, "Ошибка")
        if (SUCCESS_RESPONSE) return
        else throw BackendException(400, "Ошибка")
    }

    override suspend fun exitLobby(id: Int) {
        if (SUCCESS_RESPONSE) return
        else throw BackendException(400, "Ошибка")
    }

    override suspend fun changeStatus(id: Int) {
        if (SUCCESS_RESPONSE) return
        else throw BackendException(400, "Ошибка")
    }

    override suspend fun deletePlayer(id: Int) {
        if (SUCCESS_RESPONSE) playerList.removeIf { id == it.id }
        else throw BackendException(400, "Ошибка")
    }

    override suspend fun getCurrentPlayer(id: Int): LobbyPlayer {
        if (SUCCESS_RESPONSE)
            return LobbyPlayer(id = id, nickname = "player1", readyStatus = false)
        else throw BackendException(400, "Ошибка")
    }

    override suspend fun getHostId(): Int {
        if (SUCCESS_RESPONSE) return 0
        else throw BackendException(400, "Ошибка")
    }

    private companion object {
        const val SUCCESS_RESPONSE = true
        const val LOBBY_CODE = "Lobby"
    }
}