package com.example.uno.di

import com.example.uno.core.common.ActivityRequired
import com.example.uno.core.presentation.navigation.AppRestarter
import com.example.uno.core.presentation.navigation.GlobalNavComponentRouter
import com.example.uno.core.presentation.navigation.MainAppRestarter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    fun provideAppRestarter(
        appRestarter: MainAppRestarter
    ): AppRestarter {
        return appRestarter
    }

    @Provides
    @IntoSet
    fun provideRouterAsActivityRequired(
        router: GlobalNavComponentRouter,
    ): ActivityRequired {
        return router
    }


}