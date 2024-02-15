package com.example.uno.core.presentation

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupSimpleList() {
    layoutManager = LinearLayoutManager(context)
    (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
}
