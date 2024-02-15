package com.example.uno.data.accounts.entities

import com.example.uno.domain.game.entities.Card
import com.example.uno.domain.mainmenu.entities.Account

data class GetAccountResponseEntity(
    val id: Int,
    val nickname: String,
    val lobbyId: Int,
    val numberInTurn: Int,
    val ready: Boolean,
    val cards: List<Card>
) {
    fun toAccount() = Account(
        id = id,
        nickname = nickname
    )
}
