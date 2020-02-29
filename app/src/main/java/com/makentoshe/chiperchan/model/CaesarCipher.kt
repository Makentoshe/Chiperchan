package com.makentoshe.chiperchan.model

import java.lang.Exception

class CaesarCipher private constructor(private val shift: Int, private val alphabetLength: Int) : Cipher {
    private val AByte = 'A'.toByte()
    private val aByte = 'a'.toByte()
    private val АByte = 'A'.toByte()
    private val аByte = 'a'.toByte()

    override fun decode(string: String) = transform(string, -shift)

    override fun encode(string: String) = transform(string, shift)

    private fun transform(string: String, shift: Int) = StringBuilder().apply {
        for (c in string) {
            val ch = if (c in 'a'..'z') {
                ((c.toInt() + shift - AByte) % alphabetLength + AByte).toChar()
            } else if (c in 'A'..'Z') {
                ((c.toInt() + shift - aByte) % alphabetLength + aByte).toChar()
            } else if (c in 'А'..'Я') {
                ((c.toInt() + shift - АByte) % alphabetLength + АByte).toChar()
            } else if (c in 'а'..'я') {
                ((c.toInt() + shift - аByte) % alphabetLength + аByte).toChar()
            } else {
                throw Exception("Сука мудак ты чо эту хуйню ввел: $c")
            }

            append(ch)
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Caesar cipher"

        private val alphabetLength: Int = 26

        override fun build(parameters: Map<String, Any>): CaesarCipher {
            val shift = (parameters["shift"] as? Int?)
                ?: throw IllegalAccessException("`shift` parameter is required and should be int")
            return CaesarCipher(shift, alphabetLength)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "shift",
                    displayName = "Shift",
                    spec = Cipher.Spec(Cipher.Type.Int)
                )
            )
        }
    }
}