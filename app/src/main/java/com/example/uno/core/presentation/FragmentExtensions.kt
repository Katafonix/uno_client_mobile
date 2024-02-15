@file:Suppress("DEPRECATION", "UNCHECKED_CAST")

package com.example.uno.core.presentation

import androidx.fragment.app.Fragment

const val ARG_SCREEN = "screen"

fun <T : BaseScreen> Fragment.args(): T {
    return requireArguments().getSerializable(ARG_SCREEN) as? T
        ?: throw IllegalStateException("Screen args don't exist")
}
