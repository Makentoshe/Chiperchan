package com.makentoshe.chiperchan.model.cipher

class BovykinCipher(
    private val key1: String,
    private val key2: String,
    private val key3: String
) : Cipher {
    private val caesar = CaesarWithKeyCipher(key1)
    private val gronsfeld = GronsfeldCipher(key2)
    private val playfair = PlayfairCipher(key3)

    override fun encode(string: String): String {
        var result = string
        for (cipher in listOf(caesar, gronsfeld, playfair)) {
            for (i in 0..100) {
                result = cipher.encode(result)
            }
        }
        return result
    }

    override fun decode(string: String): String {
        var result = string
        for (cipher in listOf(playfair, gronsfeld, caesar)) {
            for (i in 0..100) {
                result = cipher.decode(result)
            }
        }
        return result
    }
}