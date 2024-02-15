package com.example.uno.data.accounts

import com.example.uno.domain.mainmenu.entities.Account

interface AccountsDataSource {

    suspend fun signInById(id: Int)

    suspend fun logOut()

    suspend fun signIn()

    suspend fun signUp(): Account

    suspend fun getAccount(): Account
}