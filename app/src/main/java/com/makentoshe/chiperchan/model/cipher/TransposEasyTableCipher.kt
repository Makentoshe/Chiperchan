package com.makentoshe.chiperchan.model.cipher

class TransposEasyTableCipher(private val key: IntArray) : Cipher {

    override fun encode(string: String): String {
        var inArray : Array<Char> = Array(string.length,{'0'})
        var n = 0
        var p = 0
        for (c in string){
            n = key[p%key.size]-1 + p/key.size * key.size
            inArray[n] = c
            p++
        }
        return inArray.joinToString("")
    }

    override fun decode(string: String) = encode(string)
}
