package com.makentoshe.chiperchan.model.cipher

import kotlin.math.ceil

class LenoreCipher(private val key1: String, private val key2: String,
                   private val a: Int, private val b: Int) : Cipher {
    private val AByte_eng = 'A'.toInt()
    private val aByte_eng = 'a'.toInt()
    private val AByte_ru = 'А'.toInt()
    private val aByte_ru = 'а'.toInt()

    override fun encode(string: String) = transformE(string)
    override fun decode(string: String) = transformD(string)

    private fun keyNu(key: String): String{
        var A: Array<Int> = Array(key.length) {0}
        var B: Array<Int> = Array(key.length) {0}
        for (i in key.indices){
            A[i] = key[i].toInt()
            B[i] = A[i]
        }
        B.sort()
        for(i in key.indices){
            for(j in key.indices){
                if(B[i]==A[j]) A[j]=i+1
            }
        }
        return A.joinToString("")
    }

    private fun transformE(str: String) = StringBuilder().apply {
        val k1 = keyNu(key1).length.toDouble()
        val s = str.length.toDouble()
        val n = ceil(s / k1)
        val arrA: Array<CharArray> = Array(n.toInt()) { CharArray(k1.toInt()) { '0' } }
        var f = 0
        for (i in 0 until n.toInt()) {
            for (j in 0 until k1.toInt()) {
                if (f < s.toInt()) {
                    arrA[i][j] = str[f]
                } else {
                    arrA[i][j] = ' '
                }
                f++
            }
        }
        val arrB: Array<CharArray> = Array(n.toInt()) { CharArray(k1.toInt()) { '0' } }
        for (i in 0 until n.toInt()) {
            for (j in 0 until k1.toInt()) {
                for (f in 0 until k1.toInt()) {
                    if (keyNu(key1)[f].toString().toInt() - 1 == j) {
                        arrB[i][f] = arrA[i][j]
                    }
                }
            }
        }
        val arrC: Array<CharArray> = Array(n.toInt()) { CharArray(k1.toInt()) { '0' } }
        var d = 0
        for (j in 0 until k1.toInt()) {
            for (i in 0 until n.toInt()) {
                d = i
                if (d != keyNu(key2)[i].toString().toInt() - 1) {
                    d = keyNu(key2)[i].toString().toInt() - 1
                    arrC[d][j] = arrB[i][j]
                } else {
                    arrC[d][j] = arrB[i][j]
                }
            }
        }
        val arrD: Array<CharArray> = Array(n.toInt()) { CharArray(k1.toInt()) { '0' } }
        for (i in 0 until n.toInt()) {
            for (j in 0 until k1.toInt()) {
                arrD[i][j]= when (arrC[i][j]) {
                    in 'a'..'z' -> {
                        ((a * (arrC[i][j].toInt() - aByte_eng + 26) + b) % 26 + aByte_eng).toChar()
                    }
                    in 'A'..'Z' -> {
                        ((a * (arrC[i][j].toInt() - AByte_eng + 26) + b) % 26 + AByte_eng).toChar()
                    }
                    in 'А'..'Я' -> {
                        ((a * (arrC[i][j].toInt() - AByte_ru + 32) + b) % 32 + AByte_ru).toChar()
                    }
                    in 'а'..'я' -> {
                        ((a * (arrC[i][j].toInt() - aByte_ru + 32) + b) % 32 + aByte_ru).toChar()
                    }
                    else -> {
                        arrC[i][j]
                    }
                }
            }
        }
        for (i in 0 until n.toInt()) {
            for (j in 0 until k1.toInt()) {
                append(arrD[i][j].toString())
            }
        }
    }.toString()

    private fun transformD(str: String)= StringBuilder().apply {
        val k1 = keyNu(key1).length
        val s = str.length
        val n = s/k1
        val arrA: Array<CharArray> = Array(n) {CharArray(k1) {'0'} }
        var f = 0
        for(i in 0 until n){
            for(j in 0 until k1){
                arrA[i][j] = str[f]
                f++
            }
        }
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
        val arr: Array<CharArray> = Array(n) {CharArray(k1) {'0'} }
        for(i in 0 until n) {
            for (j in 0 until k1) {
                arr[i][j] = when (arrA[i][j]) {
                    in 'a'..'z' -> {
                        ((d_en * (arrA[i][j].toInt() - aByte_eng + 26 - b)) % 26 + aByte_eng).toChar()
                    }
                    in 'A'..'Z' -> {
                        ((d_en * (arrA[i][j].toInt() - AByte_eng + 26 - b)) % 26 + AByte_eng).toChar()
                    }
                    in 'А'..'Я' -> {
                        ((d_ru * (arrA[i][j].toInt() - AByte_ru + 32 - b)) % 32 + AByte_ru).toChar()
                    }
                    in 'а'..'я' -> {
                        ((d_ru * (arrA[i][j].toInt() - aByte_ru + 32 - b)) % 32 + aByte_ru).toChar()
                    }
                    else -> {
                        arrA[i][j]
                    }
                }
            }
        }
        var g = 0
        val arrC: Array<CharArray> = Array(n) {CharArray(k1) {'0'} }
        for(j in 0 until k1){
            for(i in 0 until n){
                for(g in 0 until n)
                    if(keyNu(key2)[g].toString().toInt() - 1 == i){
                        arrC[g][j] = arr[i][j]
                    }
            }
        }
        val arrB: Array<CharArray> = Array(n) {CharArray(k1) {'0'} }
        var c = 0
        for(i in 0 until n) {
            for (j in 0 until k1) {
                c = j
                if (c != (keyNu(key1)[j].toString().toInt() - 1)) {
                    c = keyNu(key1)[j].toString().toInt() - 1
                    arrB[i][c] = arrC[i][j]
                } else {
                    arrB[i][c] = arrC[i][j]
                }
            }
        }
        for(i in 0 until n){
            for(j in 0 until k1){
                append(arrB[i][j].toString())
            }
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Lenore cipher"

        override fun build(parameters: Map<String, Any>): LenoreCipher {
            val key1 = (parameters["key1"] as? String?)
                ?: throw IllegalAccessException("`key1` parameter is required and should be string")
            val key2 = (parameters["key2"] as? String?)
                ?: throw IllegalAccessException("`key2` parameter is required and should be string")
            val a = (parameters["a"] as? Int?)
                ?: throw IllegalAccessException("`a` parameter is required and should be int")
            val b = (parameters["b"] as? Int?)
                ?: throw IllegalAccessException("`b` parameter is required and should be int")
            return LenoreCipher(key1, key2, a, b)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "key1",
                    displayName = "key1 (word or order of numbers)",
                    spec = Cipher.Spec(Cipher.Type.String)
                ),
                Cipher.Parameter(
                    name = "key2",
                    displayName = "key2 (word or order of numbers)",
                    spec = Cipher.Spec(Cipher.Type.String)
                ),
                Cipher.Parameter(
                    name = "a",
                    displayName = "A (only simple number)",
                    spec = Cipher.Spec(Cipher.Type.Int)
                ),
                Cipher.Parameter(
                    name = "b",
                    displayName = "B",
                    spec = Cipher.Spec(Cipher.Type.Int)
                )
            )
        }
    }
}