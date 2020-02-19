package com.makentoshe.chiperchan.ui.cipher

import android.content.Context
import android.view.View
import android.widget.TextView
import com.makentoshe.chiperchan.model.Cipher

class CipherFragmentUi(private val factory: Cipher.Factory) {

    fun create(context: Context): View {
        factory.getParameters()
        return TextView(context).apply {
            text = "SAS ASA ANUS PSA"
        }
    }
}
