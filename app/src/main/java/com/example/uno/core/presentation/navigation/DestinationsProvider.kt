package com.example.uno.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes

interface DestinationsProvider {

    @IdRes
    fun provideStartDestinationId(): Int

    @NavigationRes
    fun provideNavigationGraphId(): Int

}