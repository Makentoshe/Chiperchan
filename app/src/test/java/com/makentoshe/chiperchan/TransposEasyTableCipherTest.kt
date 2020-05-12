package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.TransposEasyTableCipher
import junit.framework.Assert
import org.junit.Test

class TransposEasyTableCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = TransposEasyTableCipher(intArrayOf(2,1)).encode("abcdef")
        Assert.assertEquals("badcfe", encoded)
        val decoded = TransposEasyTableCipher(intArrayOf(2,1)).decode(encoded)
        Assert.assertEquals("abcdef", decoded)
    }
    @Test
    fun `should encode and decode 2`() {
        val encoded = TransposEasyTableCipher(intArrayOf(3,2,1)).encode("abcdef")
        Assert.assertEquals("cbafed", encoded)
        val decoded = TransposEasyTableCipher(intArrayOf(3,2,1)).decode(encoded)
        Assert.assertEquals("abcdef", decoded)
    }
    @Test
    fun `should encode and decode 3`() {
        val encoded = TransposEasyTableCipher(intArrayOf(2,3,1)).encode("abcdef")
        Assert.assertEquals("bcaefd", encoded)
        val decoded = TransposEasyTableCipher(intArrayOf(2,3,1)).decode(encoded)
        Assert.assertEquals("abcdef", decoded)
    }
    @Test
    fun `should encode and decode 4`() {
        val encoded = TransposEasyTableCipher(intArrayOf(2,3,1)).encode("торопыжка")
        Assert.assertEquals("ортпыокаж", encoded)
        val decoded = TransposEasyTableCipher(intArrayOf(2,3,1)).decode(encoded)
        Assert.assertEquals("торопыжка", decoded)
    }
}