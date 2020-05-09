package com.makentoshe.chiperchan.model.cipher

class TransposEasyTableCipher() : Cipher {
    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()
    private val ZByte_eng = 'Z'.toInt()
    private val zByte_eng = 'z'.toInt()
    private val ZByte_ru = 'Я'.toInt()
    private val zByte_ru = 'я'.toInt()

    override fun decode(string: String) = transform(string)

    override fun encode(string: String) = transform(string)

    private fun transform(string: String) = StringBuilder().apply {
        for (c in string) {
            val ch = when (c) {
                in 'a'..'z' -> {
                    (zByte_eng - (c.toInt() - aByte_eng)).toChar()
                }
                in 'A'..'Z' -> {
                    (ZByte_eng - (c.toInt() - AByte_eng)).toChar()
                }
                in 'А'..'Я' -> {
                    (ZByte_ru - (c.toInt() - AByte_ru)).toChar()
                }
                in 'а'..'я' -> {
                    (zByte_ru - (c.toInt() - aByte_ru)).toChar()
                }
                else -> {
                    c
                }
            }

            append(ch)
        }
    }.toString()
}
