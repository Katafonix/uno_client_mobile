package com.example.uno.data.lobby.entities

import com.example.uno.domain.lobby.entities.LobbyPlayer

data class GetCurrentPlayerResponseEntity(
    val id: Int,
    val nickname: String,
    val readyStatus: Boolean,
) {
    fun toLobbyPlayer() = LobbyPlayer(
        id = id,
        nickname = nickname,
        readyStatus = readyStatus
    )
}
