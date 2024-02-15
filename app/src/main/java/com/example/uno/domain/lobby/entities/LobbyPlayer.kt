package com.example.uno.domain.lobby.entities

data class LobbyPlayer(
    val id: Int,
    val nickname: String,
    val readyStatus: Boolean,
)
