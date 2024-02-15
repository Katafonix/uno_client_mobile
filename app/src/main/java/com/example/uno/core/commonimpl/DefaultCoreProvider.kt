package com.example.uno.core.commonimpl

import android.content.Context
import com.example.uno.core.common.CommonUi
import com.example.uno.core.common.CoreProvider
import com.example.uno.core.common.ErrorHandler
import com.example.uno.core.common.Resources
import com.example.uno.core.common.logger.LogCatLogger
import com.example.uno.core.common.logger.Logger
import com.example.uno.core.presentation.navigation.AppRestarter
import kotlinx.coroutines.CoroutineScope

class DefaultCoreProvider(
    private val appContext: Context,
    override val appRestarter: AppRestarter,
    override val commonUi: CommonUi = AndroidCommonUi(appContext),
    override val resources: Resources = AndroidResources(appContext),
    override val logger: Logger = LogCatLogger(),
    override val globalScope: CoroutineScope = createDefaultGlobalScope(),
    override val errorHandler: ErrorHandler = DefaultErrorHandler(
        logger, commonUi, resources, appRestarter, globalScope
    )
) : CoreProvider