package com.makentoshe.chiperchan.model.cipher

// For more info see https://rosettacode.org/wiki/Playfair_cipher#Java
class PlayfairCipher(key: String) : Cipher {
    private val charTable = Array(5) { CharArray(5) }
    private val positions = arrayOfNulls<Pair<Int, Int>>(26)

    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()

    init {
        val s: String =
            prepareText(key.toUpperCase() + "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        val len = s.length
        var i = 0
        var k = 0
        while (i < len) {
            val c = s[i]
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c
                positions[c - 'A'] = Pair(k % 5, k / 5)
                k++
            }
            i++
        }
    }

    private fun prepareText(s: String): String {
        return s.replace("Q", "")
    }

    override fun encode(string: String): String {
        val sb = StringBuilder(string)
        var i = 0
        while (i < sb.length) {
            if (i == sb.length - 1) {
                sb.append(if (sb.length % 2 == 1) 'X' else "")
            } else if (sb[i] == sb[i + 1]) {
                sb.insert(i + 1, 'X')
            }
            i += 2
        }
        return codec(sb, 1)
    }

    override fun decode(string: String): String {
        return codec(StringBuilder(prepareText(string)), 4)
    }

    private fun codec(text: StringBuilder, direction: Int): String {
        val len = text.length
        var i = 0
        while (i < len) {
            val a = text[i]
            val b = text[i + 1]
            var row1: Int = positions[index(a)]!!.second
            var row2: Int = positions[index(b)]!!.second
            var col1: Int = positions[index(a)]!!.first
            var col2: Int = positions[index(b)]!!.first
            if (row1 == row2) {
                col1 = (col1 + direction) % 5
                col2 = (col2 + direction) % 5
            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5
                row2 = (row2 + direction) % 5
            } else {
                val tmp = col1
                col1 = col2
                col2 = tmp
            }
            text.setCharAt(i, charTable[row1][col1])
            text.setCharAt(i + 1, charTable[row2][col2])
            i += 2
        }
        return text.toString()
    }

    private fun index(c: Char): Int {
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

    class Factory : Cipher.Factory {

        override val title = "Playfair cipher"

        override fun build(parameters: Map<String, Any>): PlayfairCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be string")
            return PlayfairCipher(key)
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