package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.KardanoCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class KardanoCipherTest {
    @Test
    fun `should encode and decode`() {
        val cipher = KardanoCipher()
        val encoded = cipher.encode("пиши диссертацию")
        assertEquals("итпр ицсюдеашсии", encoded)
        val decoded = cipher.decode(encoded)
        assertEquals("пиши диссертацию", decoded)
    }
}