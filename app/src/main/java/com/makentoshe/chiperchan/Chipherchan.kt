package com.makentoshe.chiperchan

import android.app.Application
import com.makentoshe.chiperchan.di.InjectionActivityLifecycleCallbacks
import com.makentoshe.chiperchan.di.InjectionFragmentLifecycleCallbacks
import com.makentoshe.chiperchan.di.common.ApplicationModule
import com.makentoshe.chiperchan.di.common.ApplicationScope
import com.makentoshe.chiperchan.di.common.NavigationModule
import ru.terrakok.cicerone.Cicerone
import toothpick.Toothpick
import toothpick.configuration.Configuration

class Chipherchan : Application() {

    private val cicerone = Cicerone.create()

    private val toothpickConfiguration: Configuration
        get() = if (BuildConfig.DEBUG) {
            Configuration.forDevelopment().preventMultipleRootScopes()
        } else {
            Configuration.forProduction()
        }

    override fun onCreate() {
        super.onCreate()
        Toothpick.setConfiguration(toothpickConfiguration)

        injectBaseModules()
        val injectionFragmentLifecycleCallbacks = InjectionFragmentLifecycleCallbacks()
        registerActivityLifecycleCallbacks(InjectionActivityLifecycleCallbacks(injectionFragmentLifecycleCallbacks))
    }

    private fun injectBaseModules() {
        val navigationModule = NavigationModule(cicerone)
        val applicationModule = ApplicationModule(applicationContext)
        Toothpick.openScopes(ApplicationScope::class.java).installModules(navigationModule, applicationModule)
    }

}