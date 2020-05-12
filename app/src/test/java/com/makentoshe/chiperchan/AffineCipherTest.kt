package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.AffineCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class AffineCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = AffineCipher(3, 4).encode("attackatdawn")
        assertEquals("ejjekiejnesr", encoded)
        val decoded = AffineCipher(3, 4).decode(encoded)
        assertEquals("attackatdawn", decoded)
    }
    @Test
    fun `should encode and decode 2`() {
        val encoded = AffineCipher(3, 4).encode("котятки идут в бой")
        assertEquals("воъбъвь ьрэъ к зоя", encoded)
        val decoded = AffineCipher(3, 4).decode(encoded)
        assertEquals("котятки идут в бой", decoded)
    }
}