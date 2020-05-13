package com.makentoshe.chiperchan.model.cipher

import kotlin.math.ceil

class TranspositionWithKeyCipher(private val key: String) : Cipher {

    override fun decode(string: String) = transformD(string)

    override fun encode(string: String) = transformE(string)

    private fun keyNumber(key: String): String{
        var A: Array<Int> = Array(key.length, {0})
        var B: Array<Int> = Array(key.length, {0})
        for (i in 0 until  key.length){
            A[i] = key[i].toInt()
            B[i] = A[i]
        }
        B.sort()
        for(i in 0 until key.length){
            for(j in 0 until key.length){
                if(B[i]==A[j]) A[j]=i+1
            }
        }
        return A.joinToString("")
    }
    private fun transformD(str: String) = StringBuilder().apply {
        val k = keyNumber(key).length
        val s = str.length
        val n = s/k
        val arrA: Array<CharArray> = Array(n) {CharArray(k) {'0'} }
        var f = 0
        for(i in 0 until n){
            for(j in 0 until k){
                arrA[i][j] = str[f]
                f++
            }
        }
        val arrB: Array<CharArray> = Array(n) {CharArray(k) {'0'} }
        var c = 0
        for(i in 0 until n) {
            for (j in 0 until k) {
                c = j
                if (c != (keyNumber(key)[j].toString().toInt() - 1)) {
                    c = keyNumber(key)[j].toString().toInt() - 1
                    arrB[i][c] = arrA[i][j]
                } else {
                    arrB[i][c] = arrA[i][j]
                }
            }
        }
        for(i in 0 until n){
            for(j in 0 until k){
                append("${arrB[i][j]}")
            }
        }
    }.toString()

    private fun transformE(str: String) = StringBuilder().apply{
        val k = keyNumber(key).length.toDouble()
        println(keyNumber(key))
        val s = str.length.toDouble()
        val n = ceil(s / k)
        val arrA: Array<CharArray> = Array(n.toInt()) {CharArray(k.toInt()) {'0'} }
        var f = 0
        for(i in 0 until n.toInt()){
            for(j in 0 until k.toInt()){
                if (f < s.toInt()){
                    arrA[i][j] = str[f]
                }else{
                    arrA[i][j] = ' '
                }
                f++
            }
        }

        val arrB: Array<CharArray> = Array(n.toInt()) {CharArray(k.toInt()) {'0'} }
        for(i in 0 until n.toInt()){
            for(j in 0 until k.toInt()){
                for(f in 0 until k.toInt()){
                    if(keyNumber(key)[f].toString().toInt()-1 == j)  {
                        arrB[i][f] = arrA[i][j]
                    }
                }
            }
        }
        for(i in 0 until n.toInt()){
            for(j in 0 until k.toInt()){
                append("${arrB[i][j]}")
            }
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Transposition cipher with key"

        override fun build(parameters: Map<String, Any>): TranspositionWithKeyCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be string")

            return TranspositionWithKeyCipher(key)
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