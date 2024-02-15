package com.example.uno.data.accounts

import com.example.uno.data.settings.SettingsDataSource
import com.example.uno.domain.mainmenu.entities.Account
import com.example.uno.domain.mainmenu.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) : AuthRepository {

    override suspend fun setNickname(nickname: String?) {
        settingsDataSource.setNickname(nickname)
    }

    override suspend fun getNickname(): String? {
        return settingsDataSource.getNickname()
    }

    override suspend fun getId(): Int {
        return settingsDataSource.getId()
    }

    override suspend fun setId(id: Int?) {
        settingsDataSource.setId(id)
    }

    override suspend fun getAccount(): Account? {
        return settingsDataSource.getAccount()
    }
}