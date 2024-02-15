package com.example.uno.domain.mainmenu.usecases.impl

import com.example.uno.data.settings.SettingsDataSource
import com.example.uno.domain.mainmenu.repositories.AccountsRepository
import com.example.uno.domain.mainmenu.usecases.interfaces.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository,
    private val settingsDataSource: SettingsDataSource
) : SignUpUseCase {
    override suspend fun signUp() {
        val account = accountsRepository.signUp()
        settingsDataSource.setId(account.id)
        settingsDataSource.setNickname(account.nickname)
    }
}