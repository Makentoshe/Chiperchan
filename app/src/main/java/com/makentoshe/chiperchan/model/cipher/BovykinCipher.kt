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

    class Factory : Cipher.Factory {

        override val title = "Bovykin cipher"

        override fun build(parameters: Map<String, Any>): BovykinCipher {
            val key = (parameters["key1"] as? String?)
                ?: throw IllegalAccessException("`key1` parameter is required and should be String")
            val key2 = (parameters["key2"] as? String?)
                ?: throw IllegalAccessException("`key2` parameter is required and should be String")
            val key3 = (parameters["key3"] as? String?)
                ?: throw IllegalAccessException("`key3` parameter is required and should be String")
            return BovykinCipher(key, key2, key3)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "key1",
                    displayName = "Key 1",
                    spec = Cipher.Spec(Cipher.Type.String)
                ),
                Cipher.Parameter(
                    name = "key2",
                    displayName = "Key 2",
                    spec = Cipher.Spec(Cipher.Type.String)
                ),
                Cipher.Parameter(
                    name = "key3",
                    displayName = "Key 3",
                    spec = Cipher.Spec(Cipher.Type.String)
                )
            )
        }
    }
}