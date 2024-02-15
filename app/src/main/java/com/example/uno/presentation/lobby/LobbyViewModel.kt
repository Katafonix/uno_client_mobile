package com.example.uno.presentation.lobby

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.uno.core.common.BackendException
import com.example.uno.core.presentation.BaseViewModel
import com.example.uno.core.presentation.navigation.GlobalNavComponentRouter
import com.example.uno.domain.lobby.entities.LobbyPlayer
import com.example.uno.domain.lobby.usecases.interfaces.ChangeReadyStatusUseCase
import com.example.uno.domain.lobby.usecases.interfaces.DeleteLobbyPlayerUseCase
import com.example.uno.domain.lobby.usecases.interfaces.ExitLobbyUseCase
import com.example.uno.domain.lobby.usecases.interfaces.GetCurrentLobbyPlayerUseCase
import com.example.uno.domain.lobby.usecases.interfaces.GetHostIdUseCase
import com.example.uno.domain.lobby.usecases.interfaces.GetLobbyPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val getLobbyPlayersUseCase: GetLobbyPlayersUseCase,
    private val changeReadyStatusUseCase: ChangeReadyStatusUseCase,
    private val getCurrentLobbyPlayerUseCase: GetCurrentLobbyPlayerUseCase,
    private val exitLobbyUseCase: ExitLobbyUseCase,
    private val deleteLobbyPlayerUseCase: DeleteLobbyPlayerUseCase,
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val getHostIdUseCase: GetHostIdUseCase,

    ) : BaseViewModel() {

    private val players = MutableLiveData<List<LobbyPlayer>>(emptyList())
    private val currentPlayer = MutableLiveData<LobbyPlayer>()
    private val hostId = MutableLiveData(2)

    private val _viewState = MediatorLiveData<LobbyViewState>()
    val viewState: MediatorLiveData<LobbyViewState> = _viewState

    private val myId = 2

    init {
        getCurrentPlayer()
        getLobbyPlayers()
        getHostId()

        _viewState.addSource(players) { mergeSources() }
        _viewState.addSource(currentPlayer) { mergeSources() }
        _viewState.addSource(hostId) { mergeSources() }
    }

    private fun mergeSources() {
        val players = players.value ?: return
        val currentPlayer = currentPlayer.value ?: return
        val hostId = hostId.value ?: return

        _viewState.value = LobbyViewState(
            playerList = players,
            showDeleteItemButtons = currentPlayer.id == hostId,
            showStartGameButton = currentPlayer.id == hostId
        )
    }

    private fun getCurrentPlayer() {
        viewModelScope.launch {
            try {
                currentPlayer.value = getCurrentLobbyPlayerUseCase.getCurrentPlayer(myId)
            } catch (e: BackendException) {
                return@launch
            }
        }
    }

    private fun getLobbyPlayers() {
        viewModelScope.launch {
            try {
                players.value = getLobbyPlayersUseCase.getPlayers()
            } catch (e: BackendException) {
                return@launch
            }
        }
    }

    private fun getHostId() {
        viewModelScope.launch {
            try {
                hostId.value = getHostIdUseCase.getHostId()
            } catch (e: BackendException) {
                return@launch
            }
        }
    }

    fun changeReadyStatus(playerId: Int) {
        viewModelScope.launch {
            try {
                changeReadyStatusUseCase.changeReadyStatus(playerId)
            } catch (e: BackendException) {
                return@launch
            }
        }
    }

    fun deletePlayer(playerId: Int) {
        viewModelScope.launch {
            try {
                deleteLobbyPlayerUseCase.deletePlayer(playerId)
                getCurrentPlayer()
            } catch (e: BackendException) {
                return@launch
            }
        }
    }

    fun exitLobby(playerId: Int) {
        viewModelScope.launch {
            try {
                exitLobbyUseCase.exitLobby(playerId)
            } catch (e: BackendException) {
                return@launch
            }
        }
    }

    class LobbyViewState(
        val playerList: List<LobbyPlayer>,
        val showDeleteItemButtons: Boolean,
        val showStartGameButton: Boolean,
    )

}