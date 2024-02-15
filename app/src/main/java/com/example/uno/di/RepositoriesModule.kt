package com.example.uno.di

import com.example.uno.data.accounts.AccountsRepositoryImpl
import com.example.uno.data.accounts.AuthRepositoryImpl
import com.example.uno.data.lobby.LobbyRepositoryImpl
import com.example.uno.domain.lobby.LobbyRepository
import com.example.uno.domain.mainmenu.repositories.AccountsRepository
import com.example.uno.domain.mainmenu.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindLobbyRepository(
        lobbyRepositoryImpl: LobbyRepositoryImpl
    ): LobbyRepository

    @Binds
    fun bindAccountsRepository(
        accountsRepositoryImpl: AccountsRepositoryImpl
    ): AccountsRepository

    @Binds
    fun bindAuthTokenRepository(
        authTokenRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}