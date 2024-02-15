package com.example.uno.presentation.createlobby

import com.example.uno.R
import com.example.uno.core.common.BackendException
import com.example.uno.core.presentation.BaseViewModel
import com.example.uno.core.presentation.navigation.GlobalNavComponentRouter
import com.example.uno.domain.joinlobby.usecases.interfaces.JoinLobbyUseCase
import com.example.uno.domain.lobby.usecases.interfaces.CreateLobbyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateLobbyViewModel @Inject constructor(
    private val createLobbyUseCase: CreateLobbyUseCase,
    private val joinLobbyUseCase: JoinLobbyUseCase,
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : BaseViewModel() {

    private val countPlayers = MutableStateFlow(MIN_COUNT_PLAYERS)

    val viewState = countPlayers.map { count ->
        CreateLobbyViewState(count)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, CreateLobbyViewState(MIN_COUNT_PLAYERS))

    fun launchLobbyScreen() {
        viewModelScope.launch {
            try {
                val code = createLobbyUseCase.createLobby(countPlayers.value)
                joinLobbyUseCase.joinLobby(code)
                globalNavComponentRouter.launch(R.id.action_createLobbyFragment_to_lobbyFragment)
            } catch (e: BackendException) {
                commonUi.toast("Ошибка")
            }
        }
    }

    fun incrementCountPlayers() {
        countPlayers.value++
    }

    fun decrementCountPlayers() {
        countPlayers.value--
    }

    data class CreateLobbyViewState(
        val countPlayers: Int,
    ) {
        private val minQuantity: Int = MIN_COUNT_PLAYERS
        private val maxQuantity: Int = MAX_COUNT_PLAYERS
        val canDecrement: Boolean get() = countPlayers > minQuantity
        val canIncrement: Boolean get() = countPlayers < maxQuantity
    }

    private companion object {
        const val MIN_COUNT_PLAYERS = 2
        const val MAX_COUNT_PLAYERS = 4
    }
}