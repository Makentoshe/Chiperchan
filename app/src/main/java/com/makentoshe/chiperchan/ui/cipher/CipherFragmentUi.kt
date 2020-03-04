package com.makentoshe.chiperchan.ui.cipher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.makentoshe.chiperchan.R
import com.makentoshe.chiperchan.model.cipher.Cipher

class CipherFragmentUi(private val factory: Cipher.Factory) {

    fun create(context: Context): View = LayoutInflater.from(context).inflate(R.layout.cipher_fragment, null, false).also { view ->
        val params = factory.getParameters()
    }
}
