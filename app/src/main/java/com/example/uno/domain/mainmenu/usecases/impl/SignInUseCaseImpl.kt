package com.example.uno.domain.mainmenu.usecases.impl

import android.util.Log
import com.example.uno.domain.mainmenu.repositories.AccountsRepository
import com.example.uno.domain.mainmenu.repositories.AuthRepository
import com.example.uno.domain.mainmenu.usecases.interfaces.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository,
    private val authRepository: AuthRepository,
) : SignInUseCase {
    override suspend fun signIn() {
        withContext(Dispatchers.IO) {
            val account = authRepository.getAccount()

            if (account != null) {
                Log.d("SignInUseCaseImpl", "Account is not null, signing in...")
                accountsRepository.signIn(account.id)
            } else {
                Log.d("SignInUseCaseImpl", "Account is null, signing up...")
                val newAccount = accountsRepository.signUp()
                authRepository.setId(newAccount.id)
                authRepository.setNickname(newAccount.nickname)
            }

        }
    }
}