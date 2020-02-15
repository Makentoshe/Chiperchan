package com.makentoshe.chiperchan.model

interface Cipher {

    val title: String

    fun encode(string: String): String

    fun decode(string: String): String

    interface Factory {
        fun build(): Cipher
    }
}

class CaesarCipher private constructor(private val shift: Int, private val alphabetLength: Int) : Cipher {

    override val title = "Caesar cipher"

    override fun decode(string: String) = transform(string, -shift)

    override fun encode(string: String) = transform(string, shift)

    private fun transform(string: String, shift: Int) = StringBuilder().apply {
        for (c in string) {
            val ch = if (Character.isUpperCase(c)) {
                ((c.toInt() + shift - 65) % alphabetLength + 65).toChar()
            } else {
                ((c.toInt() + shift - 97) % alphabetLength + 97).toChar()
            }

            append(ch)
        }
    }.toString()

    class Factory(
        private val shift: Int, private val alphabetLength: Int
    ) : Cipher.Factory {
        override fun build() = CaesarCipher(shift, alphabetLength)
    }
}