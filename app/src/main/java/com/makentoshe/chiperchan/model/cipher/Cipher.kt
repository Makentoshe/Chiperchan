package com.makentoshe.chiperchan.model.cipher

interface Cipher {

    fun encode(string: String): String

    fun decode(string: String): String

    interface Factory {
        fun build(parameters: Map<String, Any>): Cipher
        fun getParameters(): List<Parameter>
        val title: String
    }

    class Parameter(val name: String, val displayName: String, val spec: Spec)
    class Spec(val type: Type)

    enum class Type {
        String,
        Int,
        Boolean,
        Plain
    }
}

