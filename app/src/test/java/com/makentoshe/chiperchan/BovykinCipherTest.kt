package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.BovykinCipher
import org.junit.Assert.assertEquals
import org.junit.Test

class BovykinCipherTest {
    @Test
    fun `should encode and decode`() {
        val cipher = BovykinCipher("ABC", "DEF", "GHJ")
        val message = "ABCABCABCD"
        val encoded = cipher.encode(message)
        val decoded = cipher.decode(encoded)
        assertEquals(decoded, message)
    }
}