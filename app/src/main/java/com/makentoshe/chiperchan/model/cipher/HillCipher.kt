package com.makentoshe.chiperchan.model.cipher

import java.util.*


class HillCipher : Cipher {
    private val keyMatrix = arrayOf(arrayOf(1, 2), arrayOf(2, 5))

    private fun isValidMatrix(keyMatrix: Array<Array<Int>>) {
        val det = keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]

        if (det == 0) {
            throw Error("Det equals to zero, invalid key matrix!")
        }
    }

    private fun isValidReverseMatrix(
        keyMatrix: Array<Array<Int>>,
        reverseMatrix: Array<IntArray>
    ) {
        val product =
            Array(2) { IntArray(2) }

        product[0][0] =
            (keyMatrix[0][0] * reverseMatrix[0][0] + keyMatrix[0][1] * reverseMatrix[1][0]) % 26
        product[0][1] =
            (keyMatrix[0][0] * reverseMatrix[0][1] + keyMatrix[0][1] * reverseMatrix[1][1]) % 26
        product[1][0] =
            (keyMatrix[1][0] * reverseMatrix[0][0] + keyMatrix[1][1] * reverseMatrix[1][0]) % 26
        product[1][1] =
            (keyMatrix[1][0] * reverseMatrix[0][1] + keyMatrix[1][1] * reverseMatrix[1][1]) % 26

        if (product[0][0] != 1 || product[0][1] != 0 || product[1][0] != 0 || product[1][1] != 1
        ) {
            throw Error("Invalid reverse matrix found!")
        }
    }

    private fun reverseMatrix(keyMatrix: Array<Array<Int>>): Array<IntArray> {
        val detmod26 =
            (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26 // Calc det
        val reverseMatrix =
            Array(2) { IntArray(2) }

        var factor = 1
        while (factor < 26) {
            if (detmod26 * factor % 26 == 1) {
                break
            }
            factor++
        }

        reverseMatrix[0][0] = keyMatrix[1][1] * factor % 26
        reverseMatrix[0][1] = (26 - keyMatrix[0][1]) * factor % 26
        reverseMatrix[1][0] = (26 - keyMatrix[1][0]) * factor % 26
        reverseMatrix[1][1] = keyMatrix[0][0] * factor % 26
        return reverseMatrix
    }

    override fun encode(string: String): String {
        var phrase = string
        val adder = 0
        val phraseToNum = ArrayList<Int>()
        val phraseEncoded = ArrayList<Int>()

        phrase = phrase.replace("[^a-zA-Z]".toRegex(), "").toUpperCase()

        if (phrase.length % 2 == 1) {
            phrase += "Q"
        }

        isValidMatrix(keyMatrix)

        var i = 0
        while (i < phrase.length) {
            phraseToNum.add(phrase[i].toInt() - (64 + adder))
            i++
        }

        i = 0
        while (i < phraseToNum.size) {
            val x = (keyMatrix[0][0] * phraseToNum[i] + keyMatrix[0][1] * phraseToNum[i + 1]) % 26
            val y = (keyMatrix[1][0] * phraseToNum[i] + keyMatrix[1][1] * phraseToNum[i + 1]) % 26
            phraseEncoded.add(x)
            phraseEncoded.add(y)
            i += 2
        }

        return toString(phraseEncoded)
    }


    override fun decode(string: String): String {
        var phrase = string
        val adder = 0
        val phraseToNum = ArrayList<Int>()
        val phraseDecoded = ArrayList<Int>()

        phrase = phrase.toUpperCase()

        isValidMatrix(keyMatrix)

        var i = 0
        while (i < phrase.length) {
            phraseToNum.add(phrase[i].toInt() - (64 + adder))
            i++
        }

        val revKeyMatrix: Array<IntArray> = reverseMatrix(keyMatrix)

        isValidReverseMatrix(keyMatrix, revKeyMatrix)

        i = 0
        while (i < phraseToNum.size) {
            phraseDecoded.add(
                (revKeyMatrix[0][0] * phraseToNum[i] + revKeyMatrix[0][1] * phraseToNum[i + 1]) % 26
            )
            phraseDecoded.add(
                (revKeyMatrix[1][0] * phraseToNum[i] + revKeyMatrix[1][1] * phraseToNum[i + 1]) % 26
            )
            i += 2
        }

        return toString(phraseDecoded)
    }

    private fun toString(array: List<Int>): String {
        return array.joinToString("") {
            (it + 64).toChar().toString()
        }
    }
}