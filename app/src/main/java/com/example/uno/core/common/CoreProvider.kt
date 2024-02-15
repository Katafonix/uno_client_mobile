package com.example.uno.core.common

import com.example.uno.core.common.logger.Logger
import com.example.uno.core.presentation.navigation.AppRestarter
import kotlinx.coroutines.CoroutineScope

interface CoreProvider {

    val commonUi: CommonUi

    val logger: Logger

    val resources: Resources

    val appRestarter: AppRestarter

    val globalScope: CoroutineScope

    val errorHandler: ErrorHandler

}