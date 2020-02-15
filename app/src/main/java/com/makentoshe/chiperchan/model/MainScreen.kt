package com.makentoshe.chiperchan.model

import androidx.fragment.app.Fragment
import com.makentoshe.chiperchan.common.navigator.Screen
import com.makentoshe.chiperchan.view.MainFragment

class MainScreen : Screen() {
    override val fragment: Fragment
        get() = MainFragment.Factory().build()
}