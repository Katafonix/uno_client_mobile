package com.example.uno.domain.mainmenu.usecases.impl

import com.example.uno.domain.mainmenu.entities.Account
import com.example.uno.domain.mainmenu.repositories.AccountsRepository
import com.example.uno.domain.mainmenu.usecases.interfaces.GetAccountUseCase
import javax.inject.Inject

class GetAccountUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository
) : GetAccountUseCase {
    override suspend fun getAccount(): Account {
        return accountsRepository.getAccount()
    }
}