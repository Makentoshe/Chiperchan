package com.makentoshe.chiperchan.di.common

import android.content.Context
import com.makentoshe.chiperchan.model.cipher.*
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ApplicationModule(context: Context) : Module() {

    private val cipherFactoryList = listOf(
        TransposEasyTableCipher.Factory(),
        RouteCipher.Factory(),
        TranspositionWithKeyCipher.Factory(),
        DoubleTranspositionCipher.Factory(),
        KardanoCipher.Factory(),
        CaesarCipher.Factory(),
        AffineCipher.Factory(),
        MagicSquareCipher.Factory(),
        CaesarWithKeyCipher.Factory(),
        PlayfairCipher.Factory(),
        GronsfeldCipher.Factory(),
        TrisemusCipher.Factory(),
        HillCipher.Factory(),
        PortaCipher.Factory(),
        RichelieuCipher.Factory()
    )

    init {
        bind<List<Cipher.Factory>>().toInstance(cipherFactoryList)
    }

}
