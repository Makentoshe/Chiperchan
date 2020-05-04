package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.AffineCipher
import com.makentoshe.chiperchan.model.cipher.RouteCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class RouteCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = RouteCipher(4).encode("я люблю бонч")
        assertEquals("ябб лолюню ч", encoded)
        val decoded = RouteCipher(4).decode(encoded)
        assertEquals("я люблю бонч", decoded)
    }
    @Test
    fun `should encode and decode 2`() {
        val encoded = RouteCipher(5).encode("new day will dawn and I'll walk awa")
        assertEquals("nalwdlkeyln   w   Iwa wda'awdianlla", encoded)
        val decoded = RouteCipher(5).decode(encoded)
        assertEquals("new day will dawn and I'll walk awa", decoded)
    }
}