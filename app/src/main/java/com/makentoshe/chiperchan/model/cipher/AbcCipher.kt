package com.makentoshe.chiperchan.model.cipher

class AbcCipher (private val key: String) : Cipher {
    private var c = key.toInt() % 4
    private val Caesar = CaesarCipher(key.toInt())
    private val Hill = HillCipher()
    private val Porta = PortaCipher()
    private val Kardano = KardanoCipher()
    private val Route = RouteCipher(c)

    override fun encode(string: String): String {
        var result = string
        var start = Caesar.encode(result)
        when(c){
            0 -> result = Hill.encode(start)
            1 -> result = Porta.encode(start)
            2 -> result = Kardano.encode(start)
            3 -> result = Route.encode(start)
        }
        return result
    }

    override fun decode(string: String): String {
        var start = string
        var result = ""
        when(c){
            0 -> result = Hill.decode(start)
            1 -> result = Porta.decode(start)
            2 -> result = Kardano.decode(start)
            3 -> result = Route.decode(start)
        }
        result = Caesar.decode(result)

        return result
    }

    class Factory : Cipher.Factory {

        override val title = "ABC cipher"

        override fun build(parameters: Map<String, Any>): AbcCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be String")
            return AbcCipher(key)
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