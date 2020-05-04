package com.makentoshe.chiperchan.model.cipher

import kotlin.math.ceil

class RouteCipher(private val key: Int) : Cipher {

    override fun decode(string: String) = transformD(string, key)

    override fun encode(string: String) = transformE(string, key)

    private fun transformD(str: String, key: Int) = StringBuilder().apply {
        val n = key
        val s = str.length
        val k = s/n
        val arr: Array<CharArray> = Array(n) {CharArray(k) {'0'} }
        var f = 0
        for(i in 0 until n){
            for(j in 0 until k){
                arr[i][j] = str[f]
                f++
            }
        }
        val arr2: Array<CharArray> = Array(k) {CharArray(n) {'0'} }
        for(j in 0 until k){
            for(i in 0 until n){
                arr2[j][i] = arr[i][j]
                append("${arr2[j][i]}")
            }
        }
    }.toString()

    private fun transformE(str: String, key: Int) = StringBuilder().apply{
        val k = key.toDouble()
        val s = str.length.toDouble()
        val n = ceil(s / k)
        val arr: Array<CharArray> = Array(n.toInt()) {CharArray(k.toInt()) {'0'} }
        var f = 0
        for(i in 0 until n.toInt()){
            for(j in 0 until k.toInt()){
                if (f < s.toInt()){
                    arr[i][j] = str[f]
                }else{
                    arr[i][j] = ' '
                }
                f++
            }
        }
        val arr2: Array<CharArray> = Array(k.toInt()) {CharArray(n.toInt()) {'0'} }
        for(j in 0 until k.toInt()){
            for(i in 0 until n.toInt()){
                arr2[j][i] = arr[i][j]
                append("${arr2[j][i]}")
            }
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Route Cipher"

        override fun build(parameters: Map<String, Any>): RouteCipher {
            val key = (parameters["key"] as? Int?)
                ?: throw IllegalAccessException("`key` parameter is required and should be int")
            return RouteCipher(key)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "key",
                    displayName = "key",
                    spec = Cipher.Spec(Cipher.Type.Int)
                )
            )
        }
    }
}