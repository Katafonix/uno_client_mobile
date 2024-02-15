package com.example.uno.di

import com.example.uno.data.accounts.AccountsDataSource
import com.example.uno.data.accounts.RetrofitAccountsDataSource
import com.example.uno.data.lobby.FakeLobbyDataSource
import com.example.uno.data.lobby.LobbyDataSource
import com.example.uno.data.lobby.RetrofitLobbyDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SourcesModule {

//    @Binds
//    fun bindLobbySource(
//        lobbyDataSource: RetrofitLobbyDataSource
//    ): LobbyDataSource

    @Binds
    fun bindFakeLobbySource(
        fakeLobbyDataSource: FakeLobbyDataSource
    ): LobbyDataSource

    @Binds
    fun bindAccountsSource(
        accountsDataSource: RetrofitAccountsDataSource
    ): AccountsDataSource

}