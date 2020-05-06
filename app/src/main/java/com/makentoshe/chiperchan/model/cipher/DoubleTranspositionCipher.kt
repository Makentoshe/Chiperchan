package com.makentoshe.chiperchan.model.cipher

import kotlin.math.ceil

class DoubleTranspositionCipher(private val key1: String, private val key2: String) : Cipher {

    override fun decode(string: String) = transformD(string, key1, key2)

    override fun encode(string: String) = transformE(string, key1, key2)

    private fun keyNum(key: String): String{
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
    private fun transformD(str: String, key1: String, key2:String) = StringBuilder().apply {
        val k1 = keyNum(key1).length
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
        val arrC: Array<CharArray> = Array(n) {CharArray(k1) {'0'} }
        for(j in 0 until k1){
            for(i in 0 until n){
                for(g in 0 until n)
                    if(keyNum(key2)[g].toString().toInt() - 1 == i){
                        arrC[g][j] = arrA[i][j]
                    }
            }
        }
        val arrB: Array<CharArray> = Array(n) {CharArray(k1) {'0'} }
        var c = 0
        for(i in 0 until n) {
            for (j in 0 until k1) {
                c = j
                if (c != (keyNum(key1)[j].toString().toInt() - 1)) {
                    c = keyNum(key1)[j].toString().toInt() - 1
                    arrB[i][c] = arrC[i][j]
                } else {
                    arrB[i][c] = arrC[i][j]
                }
            }
        }
        for(i in 0 until n){
            for(j in 0 until k1){
                append("${arrB[i][j]}")
            }
        }
    }.toString()

    private fun transformE(str: String, key1: String, key2: String) = StringBuilder().apply{
        val k1 = keyNum(key1).length.toDouble()
        val s = str.length.toDouble()
        val n = ceil(s / k1)
        val arrA: Array<CharArray> = Array(n.toInt()) {CharArray(k1.toInt()) {'0'} }
        var f = 0
        for(i in 0 until n.toInt()){
            for(j in 0 until k1.toInt()){
                if (f < s.toInt()){
                    arrA[i][j] = str[f]
                }else{
                    arrA[i][j] = ' '
                }
                f++
            }
        }
        val arrB: Array<CharArray> = Array(n.toInt()) {CharArray(k1.toInt()) {'0'} }
        for(i in 0 until n.toInt()){
            for(j in 0 until k1.toInt()){
                for(f in 0 until k1.toInt()){
                    if(keyNum(key1)[f].toString().toInt()-1 == j)  {
                        arrB[i][f] = arrA[i][j]
                    }
                }
            }
        }
        val arrC: Array<CharArray> = Array(n.toInt()) {CharArray(k1.toInt()) {'0'} }
        var d = 0
        for (j in 0 until k1.toInt()){
            for (i in 0 until n.toInt()){
                d = i
                if(d != keyNum(key2)[i].toString().toInt()-1){
                    d = keyNum(key2)[i].toString().toInt()-1
                    arrC[d][j] = arrB[i][j]
                }else{
                    arrC[d][j] = arrB[i][j]
                }
            }
        }
        for(i in 0 until n.toInt()){
            for(j in 0 until k1.toInt()){
                append("${arrC[i][j]}")
            }
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Double transposition cipher"

        override fun build(parameters: Map<String, Any>): DoubleTranspositionCipher {
            val key1 = (parameters["key1"] as? String?)
                ?: throw IllegalAccessException("`key1` parameter is required and should be string")
            val key2 = (parameters["key2"] as? String?)
                ?: throw IllegalAccessException("`key2` parameter is required and should be string")
            return DoubleTranspositionCipher(key1, key2)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "key1",
                    displayName = "key1",
                    spec = Cipher.Spec(Cipher.Type.String)
                ),
                Cipher.Parameter(
                    name = "key2",
                    displayName = "key2",
                    spec = Cipher.Spec(Cipher.Type.String)
                )
            )
        }
    }
}