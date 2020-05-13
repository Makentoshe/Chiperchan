package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.LenoreCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class LenoreCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = LenoreCipher("spring", "361425",3, 4).encode("The  poetry  of  Earth is never dead")
        assertEquals("Q  ut qqp rgx  zqJcz djenqe nd dyqju", encoded)
        val decoded = LenoreCipher("spring", "361425",3, 4).decode(encoded)
        assertEquals("The  poetry  of  Earth is never dead", decoded)
    }
}