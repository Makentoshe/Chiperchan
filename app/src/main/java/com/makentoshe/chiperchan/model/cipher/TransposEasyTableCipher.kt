package com.makentoshe.chiperchan.model.cipher

class TransposEasyTableCipher(private val key: IntArray) : Cipher {

    override fun encode(string: String): String {

        var str = string.toCharArray()
        var n = str.size/key.size
        var outAr : Array <Char> = Array(str.size, {'0'})
        for (i in 0 .. n-1){
            for (j in 0 .. key.size-1){
                outAr[j+i*key.size] = str[key[j]-1+i*key.size]
            }
        }
        return outAr.joinToString("")
    }

    override fun decode(string: String): String {
        var inArray: Array<Char> = Array(string.length, { '0' })
        var n = 0
        var p = 0
        for (c in string) {
            n = key[p % key.size] - 1 + p / key.size * key.size
            inArray[n] = c
            p++
        }
        return inArray.joinToString("")
    }

    class Factory : Cipher.Factory {

        override val title = "Transposition easy cipher (table)"

        override fun build(parameters: Map<String, Any>): TransposEasyTableCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be String")
            var keyAr: Array<Int> = Array(key.length, { 0 })
            var i = 0
            for (c in key) {
                keyAr[i] = Integer.parseInt(c.toString())
                i++
            }
            return TransposEasyTableCipher(keyAr.toIntArray())
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