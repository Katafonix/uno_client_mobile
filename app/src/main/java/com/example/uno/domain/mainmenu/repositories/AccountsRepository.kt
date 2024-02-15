package com.example.uno.domain.mainmenu.repositories

import com.example.uno.domain.mainmenu.entities.Account

interface AccountsRepository {


    suspend fun signIn()

    suspend fun signIn(id: Int)

    suspend fun signUp(): Account

    suspend fun getAccount(): Account

    suspend fun logOut()

}