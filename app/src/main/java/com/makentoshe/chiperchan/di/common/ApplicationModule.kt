package com.makentoshe.chiperchan.di.common

import android.content.Context
import com.makentoshe.chiperchan.model.cipher.CaesarCipher
import com.makentoshe.chiperchan.model.cipher.Cipher
import com.makentoshe.chiperchan.model.cipher.StubCipher
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ApplicationModule(context: Context) : Module() {

    private val cipherFactoryList = listOf(
        CaesarCipher.Factory(), StubCipher.Factory()
    )

    init {
        bind<List<Cipher.Factory>>().toInstance(cipherFactoryList)
    }

}
