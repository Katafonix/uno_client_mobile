package com.example.uno.core.common

interface CommonUi {

    fun toast(message: String)

    suspend fun alertDialog(config: AlertDialogConfig): Boolean

}