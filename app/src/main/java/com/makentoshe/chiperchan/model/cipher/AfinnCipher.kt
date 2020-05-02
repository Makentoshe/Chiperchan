package com.makentoshe.chiperchan.model.cipher

class AfinnCipher(private val a: Int, private val b: Int): Cipher {
    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()
    override fun decode(string: String) = transformD(string, a, b)

    private fun transformD(string: String, a: Int, b: Int)= StringBuilder().apply {
        var d_en = 0
        var d_ru = 0
        for (n in 1..100){
            if ((26 * n + 1) % a == 0) {
                d_en = (26 * n + 1) / a
                break
            }
        }
        for (n in 1..100){
            if ((32 * n + 1) % a == 0) {
                d_ru = (32 * n + 1) / a
                break
            }
        }
        for (c in string) {
            val ch = when (c) {
                in 'a'..'z' -> {
                    ((d_en * (c.toInt() - aByte_eng + 26 - b)) % 26 + aByte_eng).toChar()
                }
                in 'A'..'Z' -> {
                    ((d_en * (c.toInt() - AByte_eng + 26 - b)) % 26 + AByte_eng).toChar()
                }
                in 'А'..'Я' -> {
                    ((d_ru * (c.toInt() - AByte_ru + 32 - b)) % 32 + AByte_ru).toChar()
                }
                in 'а'..'я' -> {
                    ((d_ru * (c.toInt() - aByte_ru + 32 - b)) % 32 + aByte_ru).toChar()
                }
                else -> {
                    c
                }
            }

            append(ch)
        }
    }.toString()

    override fun encode(string: String) = transformE(string, a, b)

    private fun transformE(string: String, a: Int, b: Int)= StringBuilder().apply {
        for (c in string) {
            val ch = when (c) {
                in 'a'..'z' -> {
                    ((a * (c.toInt() - aByte_eng + 26) + b) % 26 + aByte_eng).toChar()
                }
                in 'A'..'Z' -> {
                    ((a * (c.toInt() - AByte_eng + 26) + b) % 26 + AByte_eng).toChar()
                }
                in 'А'..'Я' -> {
                    ((a * (c.toInt() - AByte_ru + 32) + b) % 32 + AByte_ru).toChar()
                }
                in 'а'..'я' -> {
                    ((a * (c.toInt() - aByte_ru + 32) + b) % 32 + aByte_ru).toChar()
                }
                else -> {
                    c
                }
            }

            append(ch)
        }
    }.toString()
}