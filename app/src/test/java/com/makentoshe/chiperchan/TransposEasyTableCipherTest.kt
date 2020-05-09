package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.TransposEasyTableCipher
import junit.framework.Assert
import org.junit.Test

class TransposEasyTableCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = TransposEasyTableCipher().encode("xyz")
        Assert.assertEquals("cba", encoded)
        val decoded = TransposEasyTableCipher().decode(encoded)
        Assert.assertEquals("xyz", decoded)
    }
}