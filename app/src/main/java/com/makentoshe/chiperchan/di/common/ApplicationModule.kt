package com.makentoshe.chiperchan.di.common

import android.content.Context
import com.makentoshe.chiperchan.model.cipher.*
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ApplicationModule(context: Context) : Module() {

    private val cipherFactoryList = listOf(
        CaesarCipher.Factory(),
        StubCipher.Factory(),
        CaesarWithKeyCipher.Factory(),
        PlayfairCipher.Factory(),
        GronsfeldCipher.Factory(),
        TrisemusCipher.Factory(),
        MagicSquareCipher.Factory(),
        AffineCipher.Factory(),
        RouteCipher.Factory()
    )

    init {
        bind<List<Cipher.Factory>>().toInstance(cipherFactoryList)
    }

}
