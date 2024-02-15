package com.example.uno.core.presentation

import androidx.lifecycle.ViewModel
import com.example.uno.core.common.CommonUi
import com.example.uno.core.common.Core
import com.example.uno.core.common.Resources
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    protected val viewModelScope: CoroutineScope by lazy {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Core.errorHandler.handleError(exception)
        }
        CoroutineScope(SupervisorJob() + Dispatchers.Main + errorHandler)
    }

    protected val resources: Resources get() = Core.resources

    protected val commonUi: CommonUi get() = Core.commonUi

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}