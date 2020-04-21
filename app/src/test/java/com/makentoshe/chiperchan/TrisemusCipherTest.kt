package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.TrisemusCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class TrisemusCipherTest {
    @Test
    fun `should encode and decode`() {
        val cipher = TrisemusCipher("developer")
        assertEquals("CGE", cipher.decode(cipher.encode("CGE")))
    }
}