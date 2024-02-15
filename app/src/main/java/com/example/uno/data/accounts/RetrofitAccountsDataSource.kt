package com.example.uno.data.accounts

import android.util.Log
import com.example.uno.data.base.retrofit.BaseRetrofitSource
import com.example.uno.data.base.retrofit.RetrofitConfig
import com.example.uno.data.settings.SettingsDataSource
import com.example.uno.domain.mainmenu.entities.Account
import javax.inject.Inject

class RetrofitAccountsDataSource @Inject constructor(
    private val config: RetrofitConfig,
    private val settings: SettingsDataSource,
) : BaseRetrofitSource(config), AccountsDataSource {

    private val accountsApi = config.retrofit.create(AccountsApi::class.java)

    override suspend fun signInById(id: Int) = wrapRetrofitExceptions {
        accountsApi.signInById(id)
    }

    override suspend fun logOut() = wrapRetrofitExceptions {
        accountsApi.logOut()
    }

    override suspend fun signIn() = wrapRetrofitExceptions {
        TODO()
    }

    override suspend fun signUp(): Account = wrapRetrofitExceptions {
        val account = accountsApi.signUp().toAccount()
        Log.d("SignInUseCaseImpl", "Account id is ${account.id}, name is ${account.nickname}")
        account
    }

    override suspend fun getAccount(): Account = wrapRetrofitExceptions {
        accountsApi.getAccount().toAccount()
    }
}