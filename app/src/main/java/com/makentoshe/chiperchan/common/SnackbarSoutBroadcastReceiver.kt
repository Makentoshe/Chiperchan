package com.makentoshe.chiperchan.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SnackbarSoutBroadcastReceiver : BroadcastReceiver() {

    private lateinit var listener: (String) -> Unit

    fun setListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION) return
        listener.invoke(intent.getStringExtra(MESSAGE) ?: return)
    }

    companion object {

        fun sendBroadcast(context: Context, message: String) {
            context.sendBroadcast(Intent(ACTION).putExtra(
                MESSAGE, message))
        }

        const val ACTION = "SnackbarSout"
        private const val MESSAGE = "SnackbarMessage"
    }
}