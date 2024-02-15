package com.example.uno.core.presentation.navigation

import android.content.Context
import com.example.uno.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultDestinationsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : DestinationsProvider {

    override fun provideStartDestinationId(): Int {
        return R.id.mainMenuFragment
    }

    override fun provideNavigationGraphId(): Int {
        return R.navigation.nav_graph
    }

}