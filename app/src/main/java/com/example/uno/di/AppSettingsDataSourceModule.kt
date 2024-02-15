package com.example.uno.di

import com.example.uno.data.settings.SettingsDataSource
import com.example.uno.data.settings.SharedPreferencesSettingsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppSettingsDataSourceModule {

    @Binds
    @Singleton
    fun bindAppSettingsDataSource(
        settingsDataSource: SharedPreferencesSettingsDataSource
    ): SettingsDataSource

}