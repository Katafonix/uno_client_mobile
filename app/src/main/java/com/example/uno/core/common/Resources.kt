package com.example.uno.core.common

interface Resources {

    fun getString(id: Int): String

    fun getString(id: Int, vararg placeholders: Any): String

}