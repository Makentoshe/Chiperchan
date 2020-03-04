package com.makentoshe.chiperchan.model.cipher

/* Just a little stub */
class StubCipher : Cipher {

    override fun encode(string: String) = string

    override fun decode(string: String) = string

    class Factory : Cipher.Factory {

        override val title: String
            get() = "Test Cipher"

        override fun build(parameters: Map<String, Any>): Cipher {
            return StubCipher()
        }

        override fun getParameters() = listOf(
            Cipher.Parameter(
                name = "StringParameter",
                displayName = "String Parameter",
                spec = Cipher.Spec(Cipher.Type.String)
            ),
            Cipher.Parameter(
                name = "IntegerParameter",
                displayName = "Integer Parameter",
                spec = Cipher.Spec(Cipher.Type.Int)
            ),
            Cipher.Parameter(
                name = "BooleanParameter",
                displayName = "Boolean Parameter",
                spec = Cipher.Spec(Cipher.Type.Boolean)
            )
        )
    }

}