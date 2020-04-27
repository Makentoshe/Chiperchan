package com.makentoshe.chiperchan.model.cipher

class MagicSquareCipher : Cipher {
    private val square = listOf(1, 6, 5, 8, 4, 0, 3, 2, 7)
    private val invertedSquare = square.sorted().map {
        square.indexOf(it)
    }

    override fun encode(string: String): String {
        return string.chunked(square.size).joinToString("") {
            transform(it, invertedSquare)
        }
    }

    override fun decode(string: String): String {
        return string.chunked(square.size).joinToString("") {
            transform(it, square)
        }
    }

    private fun transform(string: String, square: List<Int>): String {
        return square.joinToString("") { string[it].toString() }
    }

    class Factory : Cipher.Factory {

        override val title = "Magic square cipher"

        override fun build(parameters: Map<String, Any>): MagicSquareCipher {
            return MagicSquareCipher()
        }

        override fun getParameters(): List<Cipher.Parameter> = emptyList()
    }
}