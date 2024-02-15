package com.example.uno.data.lobby

import com.example.uno.data.base.retrofit.BaseRetrofitSource
import com.example.uno.data.base.retrofit.RetrofitConfig
import com.example.uno.data.lobby.entities.CreateLobbyRequestEntity
import com.example.uno.data.lobby.entities.JoinLobbyRequestEntity
import com.example.uno.domain.lobby.entities.LobbyPlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitLobbyDataSource @Inject constructor(
    config: RetrofitConfig
) : BaseRetrofitSource(config), LobbyDataSource {

    private val lobbyApi = retrofit.create(LobbyApi::class.java)

    override suspend fun getPlayers(): List<LobbyPlayer> = wrapRetrofitExceptions {
        lobbyApi.getPlayers().map { player -> player.toPLayersLobby() }
    }

    override suspend fun createLobby(numPlayers: Int): String = wrapRetrofitExceptions {
        val createLobbyRequestEntity = CreateLobbyRequestEntity(numPlayers)
        lobbyApi.createLobby(createLobbyRequestEntity).code
    }

    override suspend fun joinLobby(code: String) = wrapRetrofitExceptions {
        val joinLobbyRequestEntity = JoinLobbyRequestEntity(code)
        lobbyApi.joinLobby(joinLobbyRequestEntity)
    }

    override suspend fun exitLobby(id: Int) = wrapRetrofitExceptions {
        lobbyApi.exitLobby(id)
    }

    override suspend fun changeStatus(id: Int) = wrapRetrofitExceptions {
        lobbyApi.changeStatus(id)
    }

    override suspend fun deletePlayer(id: Int) = wrapRetrofitExceptions {
        lobbyApi.deletePlayer(id)
    }

    override suspend fun getCurrentPlayer(id: Int): LobbyPlayer = wrapRetrofitExceptions {
        lobbyApi.getPlayer(id).toLobbyPlayer()
    }

    override suspend fun getHostId(): Int = wrapRetrofitExceptions {
        lobbyApi.getHostId().hostId
    }

}