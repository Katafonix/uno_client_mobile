package com.example.uno.di

import android.content.Context
import com.example.uno.core.common.ActivityRequired
import com.example.uno.core.common.CommonUi
import com.example.uno.core.common.Core
import com.example.uno.core.common.CoreProvider
import com.example.uno.core.common.Resources
import com.example.uno.core.common.logger.Logger
import com.example.uno.core.commonimpl.DefaultCoreProvider
import com.example.uno.core.presentation.navigation.AppRestarter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet


@Module
@InstallIn(SingletonComponent::class)
class CoreProviderModule {

    @Provides
    fun provideCoreProvider(
        @ApplicationContext context: Context,
        appRestarter: AppRestarter,
    ): CoreProvider {
        return DefaultCoreProvider(
            appContext = context,
            appRestarter = appRestarter
        )
    }

    @Provides
    @ElementsIntoSet
    fun provideActivityRequiredSet(
        commonUi: CommonUi,
    ): Set<@JvmSuppressWildcards ActivityRequired> {
        val set = hashSetOf<ActivityRequired>()
        if (commonUi is ActivityRequired) set.add(commonUi)
        return set
    }

    @Provides
    fun provideCommonUi(): CommonUi {
        return Core.commonUi
    }

    @Provides
    fun provideLogger(): Logger {
        return Core.logger
    }

    @Provides
    fun provideResources(): Resources {
        return Core.resources
    }

}