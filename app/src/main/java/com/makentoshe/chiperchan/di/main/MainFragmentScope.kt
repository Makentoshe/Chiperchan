package com.makentoshe.chiperchan.di.main

import com.makentoshe.chiperchan.di.common.ApplicationScope
import com.makentoshe.chiperchan.view.main.MainFragment
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.delegate.inject

annotation class MainFragmentScope

class MainFragmentModule : Module() {

    private val router by inject<Router>()

    init {
        Toothpick.openScopes(ApplicationScope::class.java).inject(this)

        val navigator = MainFragment.Navigator(router)
        bind<MainFragment.Navigator>().toInstance(navigator)
    }
}