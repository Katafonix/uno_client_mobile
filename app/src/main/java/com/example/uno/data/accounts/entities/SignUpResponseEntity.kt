package com.example.uno.data.accounts.entities

import com.example.uno.domain.mainmenu.entities.Account

data class SignUpResponseEntity(
    val id: Int,
    val nickname: String
) {
    fun toAccount() = Account(id = id, nickname = nickname)
}