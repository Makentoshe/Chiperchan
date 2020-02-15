package com.makentoshe.chiperchan.model

interface Cipher {

    val title: String

    fun encode(byteArray: ByteArray): ByteArray

    fun decode(byteArray: ByteArray): ByteArray

}