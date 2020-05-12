package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.TranspositionWithKeyCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class TranspositionCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = TranspositionWithKeyCipher("214").encode("я люблю бонч")
        assertEquals(" ялбюл юбноч", encoded)
        val decoded = TranspositionWithKeyCipher("214").decode(encoded)
        assertEquals("я люблю бонч", decoded)
    }
    @Test
    fun `should encode and decode 2`() {
        val encoded = TranspositionWithKeyCipher("марс").encode("I'll move to Croatia")
        assertEquals("'Illm ov etoC rotaia", encoded)
        val decoded = TranspositionWithKeyCipher("марс").decode(encoded)
        assertEquals("I'll move to Croatia", decoded)
    }
}