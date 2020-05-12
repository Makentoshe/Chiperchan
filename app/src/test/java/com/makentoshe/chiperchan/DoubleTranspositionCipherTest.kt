package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.cipher.DoubleTranspositionCipher
import junit.framework.Assert.assertEquals
import org.junit.Test

class DoubleTranspositionCipherTest {
    @Test
    fun `should encode and decode`() {
        val encoded = DoubleTranspositionCipher("барс", "лис").encode("я люблю бонч")
        assertEquals("лбю  ялюобнч", encoded)
        val decoded = DoubleTranspositionCipher("барс", "лис").decode(encoded)
        assertEquals("я люблю бонч", decoded)
    }
    @Test
    fun `should encode and decode 2`() {
        val encoded = DoubleTranspositionCipher("spring", "361425").encode("The  poetry  of  Earth is never dead")
        assertEquals("E  of eev nsp  heTih rtadea dr ryeto", encoded)
        val decoded = DoubleTranspositionCipher("spring", "361425").decode(encoded)
        assertEquals("The  poetry  of  Earth is never dead", decoded)
    }
}