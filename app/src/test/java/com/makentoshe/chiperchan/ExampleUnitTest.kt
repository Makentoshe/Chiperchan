package com.makentoshe.chiperchan

import com.makentoshe.chiperchan.model.CaesarCipher
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun sas() {
        val caesarCipher = CaesarCipher.Factory(4, 26).build()
        val string = "ATTACKATONCE"
        val encodedString = caesarCipher.encode(string)
        println(encodedString)
        val decodedString = caesarCipher.decode(encodedString)
        println(decodedString)
    }
}
