package com.example.uno.presentation.mainmenu

import com.example.uno.R
import com.example.uno.core.presentation.BaseViewModel
import com.example.uno.core.presentation.navigation.GlobalNavComponentRouter
import com.example.uno.domain.mainmenu.usecases.interfaces.IsSignInUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.LogOutUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.SignInUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val isSignInUseCase: IsSignInUseCase,
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val logOutUseCase: LogOutUseCase
) : BaseViewModel() {


    fun launchCreateLobbyScreen() {
        globalNavComponentRouter.launch(R.id.action_mainMenuFragment_to_createLobbyFragment)
    }

    fun launchJoinLobbyScreen() {
        globalNavComponentRouter.launch(R.id.action_mainMenuFragment_to_joinLobbyFragment)
    }

    fun signIn() {
        viewModelScope.launch {
            signInUseCase.signIn()
            logOutUseCase.logOut()
        }
    }

}