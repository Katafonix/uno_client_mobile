package com.example.uno.di.usecases

import com.example.uno.domain.joinlobby.usecases.impl.JoinLobbyUseCaseImpl
import com.example.uno.domain.joinlobby.usecases.interfaces.JoinLobbyUseCase
import com.example.uno.domain.lobby.usecases.impl.ChangeReadyStatusUseCaseImpl
import com.example.uno.domain.lobby.usecases.impl.CreateLobbyUseCaseImpl
import com.example.uno.domain.lobby.usecases.impl.DeleteLobbyPlayerUseCaseImpl
import com.example.uno.domain.lobby.usecases.impl.ExitLobbyUseCaseImpl
import com.example.uno.domain.lobby.usecases.impl.GetCurrentLobbyPlayerUseCaseImpl
import com.example.uno.domain.lobby.usecases.impl.GetHostIdUseCaseImpl
import com.example.uno.domain.lobby.usecases.impl.GetLobbyPlayersUseCaseImpl
import com.example.uno.domain.lobby.usecases.interfaces.ChangeReadyStatusUseCase
import com.example.uno.domain.lobby.usecases.interfaces.CreateLobbyUseCase
import com.example.uno.domain.lobby.usecases.interfaces.DeleteLobbyPlayerUseCase
import com.example.uno.domain.lobby.usecases.interfaces.ExitLobbyUseCase
import com.example.uno.domain.lobby.usecases.interfaces.GetCurrentLobbyPlayerUseCase
import com.example.uno.domain.lobby.usecases.interfaces.GetHostIdUseCase
import com.example.uno.domain.lobby.usecases.interfaces.GetLobbyPlayersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface LobbyUseCasesModule {

    @Binds
    fun bindJoinLobbyUseCase(
        joinLobbyUseCaseImpl: JoinLobbyUseCaseImpl
    ): JoinLobbyUseCase

    @Binds
    fun bindChangeReadyStatusUseCase(
        changeReadyStatusUseCaseImpl: ChangeReadyStatusUseCaseImpl
    ): ChangeReadyStatusUseCase

    @Binds
    fun bindDeleteLobbyPlayerUseCase(
        deleteLobbyPlayerUseCaseImpl: DeleteLobbyPlayerUseCaseImpl
    ): DeleteLobbyPlayerUseCase

    @Binds
    fun bindExitLobbyUseCase(
        exitLobbyUseCaseImpl: ExitLobbyUseCaseImpl
    ): ExitLobbyUseCase

    @Binds
    fun bindGetCurrentLobbyPlayerUseCase(
        getCurrentLobbyPlayerUseCaseImpl: GetCurrentLobbyPlayerUseCaseImpl
    ): GetCurrentLobbyPlayerUseCase

    @Binds
    fun bindGetHostIdUseCase(
        getHostIdUseCaseImpl: GetHostIdUseCaseImpl
    ): GetHostIdUseCase

    @Binds
    fun bindGetLobbyPlayersUseCase(
        getLobbyPlayersUseCaseImpl: GetLobbyPlayersUseCaseImpl
    ): GetLobbyPlayersUseCase

    @Binds
    fun bindCreateLobbyUseCase(
        createLobbyUseCaseImpl: CreateLobbyUseCaseImpl
    ): CreateLobbyUseCase

}