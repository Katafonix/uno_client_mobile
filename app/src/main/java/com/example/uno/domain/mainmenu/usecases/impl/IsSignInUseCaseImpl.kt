package com.example.uno.domain.mainmenu.usecases.impl

import com.example.uno.data.settings.SettingsDataSource
import com.example.uno.domain.mainmenu.entities.Account
import com.example.uno.domain.mainmenu.usecases.interfaces.IsSignInUseCase
import javax.inject.Inject

class IsSignInUseCaseImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : IsSignInUseCase {
    override suspend fun isSignIn(): Account? {
        return settingsDataSource.getAccount()
    }
}