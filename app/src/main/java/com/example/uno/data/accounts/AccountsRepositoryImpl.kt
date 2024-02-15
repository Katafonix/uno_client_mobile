package com.example.uno.data.accounts

import com.example.uno.domain.mainmenu.entities.Account
import com.example.uno.domain.mainmenu.repositories.AccountsRepository
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val accountsDataSource: AccountsDataSource,
) : AccountsRepository {
    override suspend fun signIn() {
        accountsDataSource.signIn()
    }

    override suspend fun signIn(id: Int) {
        accountsDataSource.signInById(id)
    }

    override suspend fun signUp(): Account {
        return accountsDataSource.signUp()
    }

    override suspend fun getAccount(): Account {
        return accountsDataSource.getAccount()
    }

    override suspend fun logOut() {
        accountsDataSource.logOut()
    }

}