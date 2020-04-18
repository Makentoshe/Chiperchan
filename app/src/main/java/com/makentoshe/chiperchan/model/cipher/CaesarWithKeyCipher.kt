package com.makentoshe.chiperchan.model.cipher

class CaesarWithKeyCipher(private val key: String) : Cipher {
    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()

    override fun decode(string: String): String = StringBuilder().apply {
        for ((index, c) in string.withIndex()) {
            val shift = toShift(key[index % key.length])
            append(shiftChar(c, -shift))
        }
    }.toString()

    override fun encode(string: String): String = StringBuilder().apply {
        for ((index, c) in string.withIndex()) {
            val shift = toShift(key[index % key.length])
            append(shiftChar(c, shift))
        }
    }.toString()

    private fun toShift(c: Char): Int {
        return when (c) {
            in 'a'..'z' -> {
                c.toInt() - aByte_eng
            }
            in 'A'..'Z' -> {
                c.toInt() - AByte_eng
            }
            in 'А'..'Я' -> {
                c.toInt() - AByte_ru
            }
            in 'а'..'я' -> {
                c.toInt() - aByte_ru
            }
            else -> {
                0
            }
        }
    }

    private fun shiftChar(c: Char, shift: Int): Char {
        return when (c) {
            in 'a'..'z' -> {
                ((c.toInt() - aByte_eng + shift + 26) % 26 + aByte_eng).toChar()
            }
            in 'A'..'Z' -> {
                ((c.toInt() - AByte_eng + shift + 26) % 26 + AByte_eng).toChar()
            }
            in 'А'..'Я' -> {
                ((c.toInt() - AByte_ru + shift + 32) % 32 + AByte_ru).toChar()
            }
            in 'а'..'я' -> {
                ((c.toInt() - aByte_ru + shift + 32) % 32 + aByte_ru).toChar()
            }
            else -> {
                c
            }
        }
    }

    class Factory : Cipher.Factory {

        override val title = "Caesar cipher with key"

        override fun build(parameters: Map<String, Any>): CaesarWithKeyCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be String")
            return CaesarWithKeyCipher(key)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "key",
                    displayName = "Key",
                    spec = Cipher.Spec(Cipher.Type.String)
                )
            )
        }
    }
}