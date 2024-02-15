package com.example.uno.domain.mainmenu.repositories

import com.example.uno.domain.mainmenu.entities.Account

interface AuthRepository {

    suspend fun setNickname(nickname: String?)

    suspend fun getNickname(): String?

    suspend fun getId(): Int

    suspend fun setId(id: Int?)

    suspend fun getAccount(): Account?
}