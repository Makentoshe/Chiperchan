package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.HillCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class HillCipherTest {
    @Test
    fun `should encode and decode`() {
        val cipher = HillCipher()
        val encoded = cipher.encode("ABCD")
        val decoded = cipher.decode(encoded)
        assertEquals("ABCD", decoded)
    }
}