package com.makentoshe.chiperchan.model.cipher

class TrisemusCipher(
    private val key: String
) : Cipher {
    private val charTable = Array(5) { CharArray(5) }
    private val positions = arrayOfNulls<Pair<Int, Int>>(26)

    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()

    init {
        val s: String =
            key.toUpperCase() + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var k = 0
        val seen = mutableSetOf<Char>()
        for (c in s) {
            if (c in seen) {
                continue
            }

            seen.add(c)

            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c
                positions[c - 'A'] = Pair(k / 5, k % 5)
                k++
            }

            if (k == 25) {
                break
            }
        }
    }

    override fun encode(string: String): String = StringBuilder().apply {
        for (c in string) {
            val pos = positions[c.toUpperCase() - 'A']!!
            val x = (pos.first + 1) % 5
            append(charTable[x][pos.second])
        }
    }.toString()

    override fun decode(string: String): String = StringBuilder().apply {
        for (c in string) {
            val pos = positions[c.toUpperCase() - 'A']!!
            val x = (pos.first + 4) % 5
            append(charTable[x][pos.second])
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Trisemus cipher"

        override fun build(parameters: Map<String, Any>): TrisemusCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be string")
            return TrisemusCipher(key)
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