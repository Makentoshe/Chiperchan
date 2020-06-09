package com.makentoshe.chiperchan
import com.makentoshe.chiperchan.model.cipher.LbnmvCipher
import com.makentoshe.chiperchan.model.cipher.TransposEasyTableCipher
import junit.framework.Assert
import org.junit.Test
class LbnmvCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = LbnmvCipher(intArrayOf(3,4,1,2,3)).encode("Йакреведко")
        Assert.assertEquals("Взечнднуеи", encoded)
        val decoded = LbnmvCipher(intArrayOf(3,4,1,2,3)).decode(encoded)
        Assert.assertEquals("attackatdawn", decoded)
    }
}