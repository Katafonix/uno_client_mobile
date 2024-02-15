package com.example.uno.domain.mainmenu.usecases.interfaces

import com.example.uno.domain.mainmenu.entities.Account

interface GetAccountUseCase {
    suspend fun getAccount(): Account
}