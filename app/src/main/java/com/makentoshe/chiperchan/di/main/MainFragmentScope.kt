package com.makentoshe.chiperchan.di.main

import com.makentoshe.chiperchan.model.Cipher
import toothpick.config.Module
import toothpick.ktp.binding.bind

annotation class MainFragmentScope

class MainFragmentModule : Module() {

    private val cipherList = listOf<Cipher>()

    init {
        bind<List<Cipher>>().toInstance(cipherList)
    }
}