package com.makentoshe.chiperchan.model

class CaesarCipher private constructor(private val shift: Int, private val alphabetLength: Int) : Cipher {

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