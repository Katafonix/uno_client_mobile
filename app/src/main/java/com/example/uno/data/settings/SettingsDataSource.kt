package com.example.uno.data.settings

import com.example.uno.domain.mainmenu.entities.Account
import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {

    /**
     * Save auth token
     */
    fun setId(id: Int?)

    fun setNickname(nickname: String?)

    /**
     * Get the current auth token
     */
    fun getId(): Int

    fun getNickname(): String?

    /**
     * Listen for the current auth token
     */
    fun listenId(): Flow<Account?>

    fun getAccount(): Account?
}