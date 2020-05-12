package com.makentoshe.chiperchan.model.cipher

class PortaCipher : Cipher {

    override fun encode(string: String) = StringBuilder().apply {
        var c=0
        val arr: Array<IntArray> = Array(26) {IntArray(26) {0} }
        val arr1 = Array(26) {Array(26) {"0"} }
        for(i in 0 until 26){
            for(j in 0 until 26){
                arr[i][j]=c
                c++
                if(arr[i][j].toString().length == 1){
                    arr1[i][j]="00"+arr[i][j].toString()
                }else if(arr[i][j].toString().length == 2){
                    arr1[i][j]="0"+arr[i][j].toString()
                }else{
                    arr1[i][j]=arr[i][j].toString()
                }
            }
        }
        var str = string
        if(string.length%2!=0){
            str = str+"Z"
        }
        val n = str.length
        val arrA = Array(n, {0} )
        for(i in 0 until n){
            arrA[i]=str[i].toInt() - 65
        }
        for(d in 0 until n step 2) {
            for (i in 0 until 26) {
                for (j in 0 until 26) {
                    if (i == arrA[d] && j == arrA[d + 1]) {
                        append("${arr1[i][j]} ")
                    }
                }
            }
        }
    }.toString()

    override fun decode(string: String) = StringBuilder().apply{
        var c=0
        val arr: Array<IntArray> = Array(26) {IntArray(26) {0} }
        val arr1 = Array(26) {Array(26) {"0"} }
        for(i in 0 until 26){
            for(j in 0 until 26){
                arr[i][j]=c
                c++
                if(arr[i][j].toString().length == 1){
                    arr1[i][j]="00"+arr[i][j].toString()
                }else if(arr[i][j].toString().length == 2){
                    arr1[i][j]="0"+arr[i][j].toString()
                }else{
                    arr1[i][j]=arr[i][j].toString()
                }
            }
        }
        var str = string
        val n = str.length
        val k = str.length/4
        val A = Array(k, {"0"} )
        var f = 0
        for(i in 0 until k){
            A[i] = str[f].toString()+str[f+1].toString()+str[f+2].toString()
            f +=4
        }
        val arrA = Array(n/2, {0} )
        var t = 0
        for(d in 0 until k){
            for (i in 0 until 26) {
                for (j in 0 until 26) {
                    if (A[d] == arr1[i][j]) {
                        arrA[t] = i
                        arrA[t+1] = j
                        t += 2
                    }
                }
            }
        }
        var newStr = ""
        for (i in 0 until n/2){
            newStr += (arrA[i]+65).toChar()
        }
        if(newStr[newStr.length-1]=='Z'){
            for (i in 0 until  n/2-1){
                append((arrA[i]+65).toChar().toString())
            }
        }else{
            append(newStr)
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Porta cipher"

        override fun build(parameters: Map<String, Any>): PortaCipher {
            return PortaCipher()
        }

        override fun getParameters(): List<Cipher.Parameter> = emptyList()
    }
}