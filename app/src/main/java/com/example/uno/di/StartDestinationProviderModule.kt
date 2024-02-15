package com.example.uno.di

import com.example.uno.core.presentation.navigation.DefaultDestinationsProvider
import com.example.uno.core.presentation.navigation.DestinationsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StartDestinationProviderModule {

    @Binds
    fun bindStartDestinationProvider(
        startDestinationProvider: DefaultDestinationsProvider
    ): DestinationsProvider

}