package com.makentoshe.chiperchan.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.makentoshe.chiperchan.R

class MainFragmentUi {
    fun create(context: Context): View {
        return LayoutInflater.from(context).inflate(R.layout.main_fragment, null, false)
    }
}