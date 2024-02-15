package com.example.uno.domain.mainmenu.usecases.interfaces

import com.example.uno.domain.mainmenu.entities.Account

interface IsSignInUseCase {
    suspend fun isSignIn(): Account?
}