package com.example.uno.core.common

data class AlertDialogConfig(
    val title: String,
    val message: String,
    val positiveButton: String,
    val negativeButton: String? = null,
)