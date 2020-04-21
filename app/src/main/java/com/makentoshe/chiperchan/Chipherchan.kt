package com.makentoshe.chiperchan

import android.app.Application
import android.widget.Toast
import com.makentoshe.chiperchan.common.SnackbarSoutBroadcastReceiver
import com.makentoshe.chiperchan.di.InjectionActivityLifecycleCallbacks
import com.makentoshe.chiperchan.di.InjectionFragmentLifecycleCallbacks
import com.makentoshe.chiperchan.di.common.ApplicationModule
import com.makentoshe.chiperchan.di.common.ApplicationScope
import com.makentoshe.chiperchan.di.common.NavigationModule
import ru.terrakok.cicerone.Cicerone
import toothpick.Toothpick
import toothpick.configuration.Configuration

class Chipherchan : Application(), ChipherchanSout {

    init {
        sout = this
    }

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

    companion object {
        lateinit var sout: ChipherchanSout
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun snackbar(message: String) {
        SnackbarSoutBroadcastReceiver.sendBroadcast(this, message)
    }

    override fun ui(message: String) {
        TODO("Not implemented")
    }

}