package com.example.uno.core.common.logger

interface Logger {

    fun log(message: String)

    fun error(exception: Throwable, message: String? = null)

}