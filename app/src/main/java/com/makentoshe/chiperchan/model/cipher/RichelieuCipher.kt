package com.makentoshe.chiperchan.model.cipher

class RichelieuCipher(key: String) : Cipher {

    private val segments = key.split(" ").map(::Segment)

    override fun decode(string: String) = try {
        val string = string.replace("\\s".toRegex(), "")
        val stringBuilder = StringBuilder()
        var tail = 0
        segments.forEach { segment ->
            val sub = string.subSequence(tail, tail + segment.code.size)
            val new = segment.code.mapIndexed { index, it -> index to sub[it - 1] }.map { it.second }
            stringBuilder.append(new.joinToString(""))
            tail += segment.code.size
        }
        stringBuilder.toString()
    } catch (e: Exception) {
        string
    }

    override fun encode(string: String) = try {
        val string = string.replace("\\s".toRegex(), "")
        val stringBuilder = StringBuilder()
        var tail = 0
        segments.forEach { segment ->
            val sub = string.subSequence(tail, tail + segment.code.size)
            val new =
                segment.code.mapIndexed { index, it -> it to sub[index] }.sortedBy { it.first }.map { it.second }
            stringBuilder.append(new.joinToString("")).append(" ")
            tail += segment.code.size
        }
        stringBuilder.toString().trimEnd()
    } catch (e: Exception) {
        string
    }

    private class Segment(keySegment: String) {
        val code = keySegment.map { it.toString().toInt() }.toIntArray()
    }

    class Factory : Cipher.Factory {

        override val title: String
            get() = "Richelieu cipher"

        override fun build(parameters: Map<String, Any>): Cipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalArgumentException("Key parameter is required and should be String")
            return RichelieuCipher(key)
        }

        override fun getParameters() = listOf(
            Cipher.Parameter(
                name = "key",
                displayName = "Key (using space as segments separator)",
                spec = Cipher.Spec(Cipher.Type.String)
            )
        )
    }
}