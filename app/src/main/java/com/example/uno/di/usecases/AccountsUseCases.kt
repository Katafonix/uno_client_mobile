package com.example.uno.di.usecases

import com.example.uno.domain.mainmenu.usecases.impl.GetAccountUseCaseImpl
import com.example.uno.domain.mainmenu.usecases.impl.IsSignInUseCaseImpl
import com.example.uno.domain.mainmenu.usecases.impl.LogOutUseCaseImpl
import com.example.uno.domain.mainmenu.usecases.impl.SignInUseCaseImpl
import com.example.uno.domain.mainmenu.usecases.impl.SignUpUseCaseImpl
import com.example.uno.domain.mainmenu.usecases.interfaces.GetAccountUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.IsSignInUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.LogOutUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.SignInUseCase
import com.example.uno.domain.mainmenu.usecases.interfaces.SignUpUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountsUseCases {

    @Binds
    fun bindSignInUseCase(
        signInUseCaseImpl: SignInUseCaseImpl
    ): SignInUseCase

    @Binds
    fun bindSignUpUseCase(
        signUpUseCaseImpl: SignUpUseCaseImpl
    ): SignUpUseCase

    @Binds
    fun bindIsSignInUseCase(
        isSignInUseCaseImpl: IsSignInUseCaseImpl
    ): IsSignInUseCase

    @Binds
    fun bindGetAccountUseCase(
        getAccountUseCaseImpl: GetAccountUseCaseImpl
    ): GetAccountUseCase

    @Binds
    fun bindLogOutUseCase(
        logOutUseCaseImpl: LogOutUseCaseImpl
    ): LogOutUseCase

}