package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.PlayfairCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class PlayfairCipherTest {
    @Test
    fun `should encode and decode back`() {
        val cipher = PlayfairCipher("abc")
        assertEquals("CDEG", cipher.decode(cipher.encode("CDEG")))
    }
}