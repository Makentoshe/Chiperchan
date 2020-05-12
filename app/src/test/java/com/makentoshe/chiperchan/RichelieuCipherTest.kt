package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.RichelieuCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class RichelieuCipherTest {

    private val richelieuCipher = RichelieuCipher("231 4132 3142")

    @Test
    fun testShouldEncodeString() {
        val string = "шифр ришелье"
        val result = richelieuCipher.encode(string)
        assertEquals("фши ршир лееь", result)
    }

    @Test
    fun testShouldDecodeString() {
        val string = "фши ршир лееь"
        val result = richelieuCipher.decode(string)
        assertEquals("шифрришелье", result)
    }
}