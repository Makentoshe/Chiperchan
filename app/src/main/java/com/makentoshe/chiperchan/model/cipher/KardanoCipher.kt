package com.makentoshe.chiperchan.model.cipher

class KardanoCipher : Cipher {

    override fun decode(str: String) = StringBuilder().apply {
        val k = 4
        val m = 4
        val arr: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        var f = 0
        for(i in 0 until k){
            for(j in 0 until m){
                arr[i][j] = str[f]
                f++
            }
        }
        val arrA: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        arrA[0][0]=arr[0][2]
        arrA[0][1]=arr[1][1]
        arrA[0][2]=arr[3][0]
        arrA[0][3]=arr[3][2]
        val arrB: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrB[i][j]=arr[k-1-j][i]
            }
        }
        arrA[1][0]=arrB[0][2]
        arrA[1][1]=arrB[1][1]
        arrA[1][2]=arrB[3][0]
        arrA[1][3]=arrB[3][2]
        val arrC: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrC[i][j]=arrB[k-1-j][i]
            }
        }
        arrA[2][0]=arrC[0][2]
        arrA[2][1]=arrC[1][1]
        arrA[2][2]=arrC[3][0]
        arrA[2][3]=arrC[3][2]
        val arrD: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrD[i][j]=arrC[k-1-j][i]
            }
        }
        arrA[3][0]=arrD[0][2]
        arrA[3][1]=arrD[1][1]
        arrA[3][2]=arrD[3][0]
        arrA[3][3]=arrD[3][2]
        for(i in 0 until k){
            for(j in 0 until m){
                append(arrA[i][j].toString())
            }
        }
    }.toString()

    override fun encode(str: String) = StringBuilder().apply {
        val k = 4
        val m = 4
        val s = str.length
        val arr: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        var f = 0
        for(i in 0 until k){
            for(j in 0 until m){
                if (f < s.toInt()){
                    arr[i][j] = str[f]
                }else{
                    arr[i][j] = ' '
                }
                f++
            }
        }
        val arrA: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        arrA[0][2]=arr[0][0]
        arrA[1][1]=arr[0][1]
        arrA[3][0]=arr[0][2]
        arrA[3][2]=arr[0][3]
        val arrB: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrB[i][j]=arrA[k-1-j][i]
            }
        }
        arrB[0][2]=arr[1][0]
        arrB[1][1]=arr[1][1]
        arrB[3][0]=arr[1][2]
        arrB[3][2]=arr[1][3]
        val arrC: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrC[i][j]=arrB[k-1-j][i]
            }
        }
        arrC[0][2]=arr[2][0]
        arrC[1][1]=arr[2][1]
        arrC[3][0]=arr[2][2]
        arrC[3][2]=arr[2][3]
        val arrD: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrD[i][j]=arrC[k-1-j][i]
            }
        }
        arrD[0][2]=arr[3][0]
        arrD[1][1]=arr[3][1]
        arrD[3][0]=arr[3][2]
        arrD[3][2]=arr[3][3]
        val arrE: Array<CharArray> = Array(k) {CharArray(m) {'0'} }
        for(i in 0 until k){
            for(j in 0 until m){
                arrE[i][j]=arrD[k-1-j][i]
                append(arrE[i][j].toString())
            }
        }
    }.toString()


    class Factory : Cipher.Factory {

        override val title = "Kardano Cipher"

        override fun build(parameters: Map<String, Any>): KardanoCipher {
            return KardanoCipher()
        }

        override fun getParameters(): List<Cipher.Parameter> = emptyList()
    }
}