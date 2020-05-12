package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.PortaCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class PortaCipherTest {
    @Test
    fun `should encode and decode`() {
        val cipher = PortaCipher()
        val encoded = cipher.encode("ANDROID")
        assertEquals("013 095 372 103 ", encoded)
        val decoded = cipher.decode(encoded)
        assertEquals("ANDROID", decoded)
    }
}