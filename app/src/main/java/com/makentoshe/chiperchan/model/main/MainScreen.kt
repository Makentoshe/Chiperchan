package com.makentoshe.chiperchan.model.main

import androidx.fragment.app.Fragment
import com.makentoshe.chiperchan.common.navigator.Screen
import com.makentoshe.chiperchan.view.main.MainFragment

class MainScreen : Screen() {
    override val fragment: Fragment
        get() = MainFragment.Factory().build()
}