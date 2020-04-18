package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.CaesarWithKeyCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class CaesarWithKeyCipherTest {
    @Test
    fun `should encode simple strings`() {
        val cipher = CaesarWithKeyCipher("abc")
        assertEquals("abc", cipher.encode("aaa"))
        assertEquals("ABC", cipher.encode("AAA"))
        assertEquals("АБВ", cipher.encode("ААА"))
        assertEquals("абв", cipher.encode("ааа"))
    }

    @Test
    fun `should encode with overflow`() {
        val cipher = CaesarWithKeyCipher("abc")
        assertEquals("zab", cipher.encode("zzz"))
        assertEquals("ZAB", cipher.encode("ZZZ"))
        assertEquals("яаб", cipher.encode("яяя"))
        assertEquals("ЯАБ", cipher.encode("ЯЯЯ"))
    }

    @Test
    fun `should decode simple strings`() {
        val cipher = CaesarWithKeyCipher("abc")
        assertEquals("aaa", cipher.decode("abc"))
        assertEquals("AAA", cipher.decode("ABC"))
        assertEquals("ААА", cipher.decode("АБВ"))
        assertEquals("ааа", cipher.decode("абв"))
    }

    @Test
    fun `should decode with underflow`() {
        val cipher = CaesarWithKeyCipher("abc")
        assertEquals("zzz", cipher.decode("zab"))
        assertEquals("ZZZ", cipher.decode("ZAB"))
        assertEquals("яяя", cipher.decode("яаб"))
        assertEquals("ЯЯЯ", cipher.decode("ЯАБ"))
    }
}