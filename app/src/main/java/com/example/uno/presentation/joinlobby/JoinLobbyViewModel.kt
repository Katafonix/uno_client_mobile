package com.example.uno.presentation.joinlobby

import com.example.uno.R
import com.example.uno.core.common.AlertDialogConfig
import com.example.uno.core.common.BackendException
import com.example.uno.core.common.EmptyCodeException
import com.example.uno.core.presentation.BaseViewModel
import com.example.uno.core.presentation.navigation.GlobalNavComponentRouter
import com.example.uno.domain.joinlobby.usecases.interfaces.JoinLobbyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinLobbyViewModel @Inject constructor(
    private val joinLobbyUseCase: JoinLobbyUseCase,
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : BaseViewModel() {

    private val progressStateFlow = MutableStateFlow(false)
    private val codeErrorStateFlow = MutableStateFlow<String?>(null)

    val stateLiveValue = combine(
        progressStateFlow,
        codeErrorStateFlow,
        ::merge
    )

    private fun merge(
        inProgress: Boolean,
        wordError: String?,
    ): JoinLobbyViewState {
        return JoinLobbyViewState(
            inProgress = inProgress,
            codeError = wordError,
        )
    }

    fun joinLobby(code: String) {
        viewModelScope.launch {
            try {
                progressStateFlow.value = true
                joinLobbyUseCase.joinLobby(code)
                globalNavComponentRouter.launch(R.id.action_joinLobbyFragment_to_lobbyFragment)
            } catch (e: EmptyCodeException) {
                codeErrorStateFlow.value = resources.getString(R.string.empty_code)
            } catch (e: BackendException) {
                commonUi.alertDialog(
                    AlertDialogConfig(
                        title = "Не удалось подключиться к лобби",
                        message = "Лобби с таким кодом не существует",
                        positiveButton = "Ок"
                    )
                )
            } finally {
                progressStateFlow.value = false
            }
        }
    }

    fun cleanWordError() {
        codeErrorStateFlow.value = null
    }

    class JoinLobbyViewState(
        private val inProgress: Boolean,
        val codeError: String?,
    ) {
        val enableButtons: Boolean get() = !inProgress
        val showProgressBar: Boolean get() = inProgress
    }
}