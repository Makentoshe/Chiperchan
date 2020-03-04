package com.makentoshe.chiperchan.di.cipher

import com.makentoshe.chiperchan.di.common.ApplicationScope
import com.makentoshe.chiperchan.model.cipher.Cipher
import com.makentoshe.chiperchan.view.cipher.CipherFragment
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.delegate.inject

class CipherFragmentModule(fragment: CipherFragment) : Module() {

    private val cipherFactoryList by inject<List<Cipher.Factory>>()

    init {
        Toothpick.openScopes(ApplicationScope::class.java).inject(this)

        val cipherFactory = cipherFactoryList.first { it.title == fragment.arguments.cipherTitle }
        bind<Cipher.Factory>().toInstance(cipherFactory)
    }
}