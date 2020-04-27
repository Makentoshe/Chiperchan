package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.MagicSquareCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class MagicSquareCipherTest {
    @Test
    fun `should encode and decode`() {
        val cipher = MagicSquareCipher()
        val encoded = cipher.encode("ПРОТИВНИК")
        assertEquals("ВПИНИОРКТ", encoded)
        val decoded = cipher.decode(encoded)
        assertEquals("ПРОТИВНИК", decoded)
    }
}