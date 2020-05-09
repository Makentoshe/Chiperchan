package com.makentoshe.chiperchan.model.cipher

import kotlin.math.ceil

class RouteCipher(private val key: Int) : Cipher {

    override fun decode(string: String) = transformD(string)

    override fun encode(string: String) = transformE(string)

    private fun transformD(str: String) = StringBuilder().apply {
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
        for(j in 0 until k){
            for(i in 0 until n){
               append(arr[i][j])
            }
        }
    }.toString()

    private fun transformE(str: String) = StringBuilder().apply{
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
        for(j in 0 until k.toInt()){
            for(i in 0 until n.toInt()){
                append(arr[j][i].toString())
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