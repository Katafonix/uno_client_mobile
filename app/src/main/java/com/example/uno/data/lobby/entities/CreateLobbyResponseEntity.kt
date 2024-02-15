package com.example.uno.data.lobby.entities

data class CreateLobbyResponseEntity(
    val id: Int,
    val code: String,
    val numPlayers: Int,
    val hostId: Int
)
