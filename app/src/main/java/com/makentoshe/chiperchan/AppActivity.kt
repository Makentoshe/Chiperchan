package com.makentoshe.chiperchan

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.makentoshe.chiperchan.common.SnackbarSoutBroadcastReceiver
import com.makentoshe.chiperchan.common.navigator.Navigator
import com.makentoshe.chiperchan.model.main.MainScreen
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.ktp.delegate.inject

class AppActivity : AppCompatActivity() {

    private val navigator = Navigator(this, R.id.main_container)

    private val router by inject<Router>()
    private val navigatorHolder by inject<NavigatorHolder>()
    private val snackbarSoutBroadcastReceiver by inject<SnackbarSoutBroadcastReceiver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snackbarSoutBroadcastReceiver.setListener { message ->
            Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG).show()
        }

        if (savedInstanceState != null) return

        when (intent.action) {
            Intent.ACTION_MAIN -> router.newRootScreen(MainScreen())
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(SnackbarSoutBroadcastReceiver.ACTION)
        registerReceiver(snackbarSoutBroadcastReceiver, filter)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(snackbarSoutBroadcastReceiver)
    }

}


