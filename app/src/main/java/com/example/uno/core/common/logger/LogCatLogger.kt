package com.example.uno.core.common.logger

import android.util.Log

class LogCatLogger : Logger {

    override fun log(message: String) {
        Log.d("AndroidLogger", message)
    }

    override fun error(exception: Throwable, message: String?) {
        Log.e("AndroidLogger", message, exception)
    }

}