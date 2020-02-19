package com.makentoshe.chiperchan.model.cipher

import androidx.fragment.app.Fragment
import com.makentoshe.chiperchan.common.navigator.Screen
import com.makentoshe.chiperchan.view.cipher.CipherFragment

class CipherScreen(private val cipherTitle: String) : Screen() {
    override val fragment: Fragment
        get() = CipherFragment.Factory().build(cipherTitle)
}