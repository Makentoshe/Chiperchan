package com.makentoshe.chiperchan.model.cipher

class CaesarCipher(private val shift: Int) : Cipher {
    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()

    override fun decode(string: String) = transform(string, -shift)

    override fun encode(string: String) = transform(string, shift)

    private fun transform(string: String, shift: Int) = StringBuilder().apply {
        for (c in string) {
            val ch = when (c) {
                in 'a'..'z' -> {
                    ((c.toInt() + shift - aByte_eng + 26) % 26 + aByte_eng).toChar()
                }
                in 'A'..'Z' -> {
                    ((c.toInt() + shift - AByte_eng + 26) % 26 + AByte_eng).toChar()
                }
                in 'А'..'Я' -> {
                    ((c.toInt() + shift - AByte_ru + 32) % 32 + AByte_ru).toChar()
                }
                in 'а'..'я' -> {
                    ((c.toInt() + shift - aByte_ru + 32) % 32 + aByte_ru).toChar()
                }
                else -> {
                    c
                }
            }

            append(ch)
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Caesar cipher"

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