package com.makentoshe.chiperchan.model.cipher

class CaesarCipher private constructor(private val shift: Int) :
    Cipher {
    private val AByte = 'A'.toInt()
    private val aByte = 'a'.toInt()
    private val АByte = 'А'.toInt()
    private val аByte = 'а'.toInt()

    override fun decode(string: String) = transform(string, -shift)

    override fun encode(string: String) = transform(string, shift)

    private fun transform(string: String, shift: Int) = StringBuilder().apply {
        for (c in string) {
            val ch = if (c in 'a'..'z') {
                ((c.toInt() + shift - aByte) % 26 + aByte).toChar()
            } else if (c in 'A'..'Z') {
                ((c.toInt() + shift - AByte) % 26 + AByte).toChar()
            } else if (c in 'А'..'Я') {
                ((c.toInt() + shift - АByte) % 33 + АByte).toChar()
            } else if (c in 'а'..'я') {
                ((c.toInt() + shift - аByte) % 33 + аByte).toChar()
            } else {
                c
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
            return CaesarCipher(shift)
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