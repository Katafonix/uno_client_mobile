package com.example.uno.data.lobby

import com.example.uno.data.lobby.entities.CreateLobbyRequestEntity
import com.example.uno.data.lobby.entities.CreateLobbyResponseEntity
import com.example.uno.data.lobby.entities.GetCurrentPlayerResponseEntity
import com.example.uno.data.lobby.entities.GetHostIdResponseEntity
import com.example.uno.data.lobby.entities.GetPlayersLobbyResponseEntity
import com.example.uno.data.lobby.entities.JoinLobbyRequestEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface LobbyApi {

    @GET("players")
    suspend fun getPlayers(): List<GetPlayersLobbyResponseEntity>

    @POST("lobby")
    suspend fun createLobby(@Body createLobbyRequestEntity: CreateLobbyRequestEntity): CreateLobbyResponseEntity

    @POST("lobby/join-lobby}")
    suspend fun joinLobby(@Body body: JoinLobbyRequestEntity)

    @POST("lobby/exit{id}")
    suspend fun exitLobby(@Path("id") id: Int)

    @PATCH("change-status{id}")
    suspend fun changeStatus(@Path("id") id: Int)

    @POST("delete-player{id}")
    suspend fun deletePlayer(@Path("id") id: Int)

    @GET("current-player{id}")
    suspend fun getPlayer(@Path("id") id: Int): GetCurrentPlayerResponseEntity

    @GET("isHost{id}")
    suspend fun getHostId(): GetHostIdResponseEntity

}