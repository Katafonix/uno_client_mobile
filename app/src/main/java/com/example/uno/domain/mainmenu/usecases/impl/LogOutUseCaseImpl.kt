package com.example.uno.domain.mainmenu.usecases.impl

import com.example.uno.domain.mainmenu.repositories.AccountsRepository
import com.example.uno.domain.mainmenu.usecases.interfaces.LogOutUseCase
import javax.inject.Inject

class LogOutUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository
) : LogOutUseCase {
    override suspend fun logOut() {
        accountsRepository.logOut()
    }
}