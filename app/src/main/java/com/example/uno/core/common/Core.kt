package com.example.uno.core.common

import com.example.uno.core.common.logger.Logger
import com.example.uno.core.presentation.navigation.AppRestarter
import kotlinx.coroutines.CoroutineScope

object Core {

    private lateinit var coreProvider: CoreProvider

    val commonUi: CommonUi get() = coreProvider.commonUi

    val resources: Resources get() = coreProvider.resources

    val logger: Logger get() = coreProvider.logger

    val globalScope: CoroutineScope get() = coreProvider.globalScope

    val errorHandler: ErrorHandler get() = coreProvider.errorHandler

    val appRestarter: AppRestarter get() = coreProvider.appRestarter

    fun init(coreProvider: CoreProvider) {
        Core.coreProvider = coreProvider
    }

}