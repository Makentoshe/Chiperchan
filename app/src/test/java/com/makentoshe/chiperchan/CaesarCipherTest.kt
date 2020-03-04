package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.CaesarCipher
import org.junit.Test

class CaesarCipherTest {

    @Test
    fun encode() {
        println(CaesarCipher(1).encode("я"))
    }
}