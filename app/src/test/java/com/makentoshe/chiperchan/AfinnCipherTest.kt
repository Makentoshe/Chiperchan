package com.makentoshe.chiperchan
import com.makentoshe.chiperchan.model.cipher.AfinnCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class AfinnCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = AfinnCipher(3, 4).encode("attackatdawn")
        assertEquals("ejjekiejnesr", encoded)
        val decoded = AfinnCipher(3, 4).decode(encoded)
        assertEquals("attackatdawn", decoded)
    }
    @Test
    fun `should encode and decode 2`() {
        val encoded = AfinnCipher(3, 4).encode("котятки идут в бой")
        assertEquals("воъбъвь ьрэъ к зоя", encoded)
        val decoded = AfinnCipher(3, 4).decode(encoded)
        assertEquals("котятки идут в бой", decoded)
    }
}