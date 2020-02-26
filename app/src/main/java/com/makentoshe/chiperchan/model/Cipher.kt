package com.makentoshe.chiperchan.model

import android.view.View

interface Cipher {

    fun encode(string: String): String

    fun decode(string: String): String

    interface Factory {
        fun build(parameters: Map<String, Any>): Cipher
        fun getParameters(): List<Parameter>
        val title: String
    }

    class Parameter(val name: String, val displayName: String, val spec: Spec, val viewId: Int = View.generateViewId())
    class Spec(val type: Type)

    enum class Type {
        String,
        Int,
        Boolean
    }
}

class CaesarCipher private constructor(private val shift: Int, private val alphabetLength: Int) : Cipher {

    override fun decode(string: String) = transform(string, -shift)

    override fun encode(string: String) = transform(string, shift)

    private fun transform(string: String, shift: Int) = StringBuilder().apply {
        for (c in string) {
            val ch = if (Character.isUpperCase(c)) {
                ((c.toInt() + shift - 65) % alphabetLength + 65).toChar()
            } else {
                ((c.toInt() + shift - 97) % alphabetLength + 97).toChar()
            }

            append(ch)
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Caesar cipher"

        private val alphabetLength: Int = 26

        override fun build(parameters: Map<String, Any>): CaesarCipher {
            val shift = (parameters["shift"] as? Int?)
                ?: throw IllegalAccessException("`shift` parameter is required and should be int")
            return CaesarCipher(shift, alphabetLength)
        }

        override fun getParameters(): List<Cipher.Parameter> {
            return listOf(
                Cipher.Parameter(
                    name = "shift",
                    displayName = "Shift",
                    spec = Cipher.Spec(Cipher.Type.Int)
                ),
                Cipher.Parameter(
                    name = "string",
                    displayName = "String",
                    spec = Cipher.Spec(Cipher.Type.String)
                ),
                Cipher.Parameter(
                    name = "bool",
                    displayName = "Boolean",
                    spec = Cipher.Spec(Cipher.Type.Boolean)
                )
            )
        }
    }
}