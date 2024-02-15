package com.example.uno.data.lobby.entities

import com.example.uno.domain.lobby.entities.LobbyPlayer

data class GetPlayersLobbyResponseEntity(
    val id: Int,
    val nickname: String,
    val readyStatus: Boolean,
) {
    fun toPLayersLobby(): LobbyPlayer {
        return LobbyPlayer(
            id = id,
            nickname = nickname,
            readyStatus = readyStatus,
        )
    }
}
