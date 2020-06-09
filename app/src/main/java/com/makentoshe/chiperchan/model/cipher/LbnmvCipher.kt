package com.makentoshe.chiperchan.model.cipher

class LbnmvCipher (private val key: IntArray) : Cipher {

        val key_a = key[0]
        val key_b = key[1]
        var key_tr: Array<Int> = Array(key.size-2, { 0 })
    override fun encode(string: String) = AffineCipher(key_a, key_b).encode(transformE(string, key_a, key_b))

    private fun transformE(string: String, key_a: Int, key_b: Int)= StringBuilder().apply {

        for (i in 0..key.size-3){
            key_tr[i] = key[i+2]
        }
        val RU_A= TransposEasyTableCipher(key_tr.toIntArray()).encode("АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ").toCharArray()
        val RU_a= TransposEasyTableCipher(key_tr.toIntArray()).encode("абвгдежзийклмнопрстуфхцчшщъыьэюя").toCharArray()
        val EN_A= TransposEasyTableCipher(key_tr.toIntArray()).encode("ABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray()
        val EN_a= TransposEasyTableCipher(key_tr.toIntArray()).encode("abcdefghijklmnopqrstuvwxyz").toCharArray()

        val Ru_A_new= ("АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ").toCharArray()
        val RU_a_new= ("абвгдежзийклмнопрстуфхцчшщъыьэюя").toCharArray()
        val EN_A_new= ("ABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray()
        val EN_a_new= ("abcdefghijklmnopqrstuvwxyz").toCharArray()
        var d = '0'
        for (c in string) {
            val ch = when (c) {
                in 'a'..'z' -> {
                    for (i in 0..25){
                        if (EN_a_new[i] == c) d = EN_a[i]
                    }
                    d
                }
                in 'A'..'Z' -> {
                    for (i in 0..25){
                        if (EN_A_new[i] == c) d = EN_A[i]
                    }
                    d
                }
                in 'А'..'Я' -> {
                    for (i in 0..31){
                        if (Ru_A_new[i] == c) d = RU_A[i]
                    }
                    d
                }
                in 'а'..'я' -> {
                    for (i in 0..31){
                        if (RU_a_new[i] == c) d = RU_a[i]
                    }
                    d
                }
                else -> {
                    c
                }
            }

            append(ch)
        }
    }.toString()

    override fun decode(string: String)= transformD(string, key_a, key_b)

    private fun transformD(string: String, key_a: Int, key_b: Int)= StringBuilder().apply {

        for (i in 0..key.size-3){
            key_tr[i] = key[i+2]
        }
        val RU_A= TransposEasyTableCipher(key_tr.toIntArray()).encode("АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ").toCharArray()
        val RU_a= TransposEasyTableCipher(key_tr.toIntArray()).encode("абвгдежзийклмнопрстуфхцчшщъыьэюя").toCharArray()
        val EN_A= TransposEasyTableCipher(key_tr.toIntArray()).encode("ABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray()
        val EN_a= TransposEasyTableCipher(key_tr.toIntArray()).encode("abcdefghijklmnopqrstuvwxyz").toCharArray()

        val Ru_A_new= ("АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ").toCharArray()
        val RU_a_new= ("абвгдежзийклмнопрстуфхцчшщъыьэюя").toCharArray()
        val EN_A_new= ("ABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray()
        val EN_a_new= ("abcdefghijklmnopqrstuvwxyz").toCharArray()
        var d = '0'
        /*val RU_A= ("АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ").toCharArray()
        val RU_a= ("абвгдежзийклмнопрстуфхцчшщъыьэюя").toCharArray()
        val EN_A= ("ABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray()
        val EN_a= ("abcdefghijklmnopqrstuvwxyz").toCharArray()*/

        val new_string = AffineCipher(key_a, key_b).decode(string)

        for (c in new_string) {
            val ch = when (c) {
                in 'a'..'z' -> {
                    for (i in 0..25){
                        if (EN_a[i] == c) d = EN_a_new[i]
                    }
                    d
                }
                in 'A'..'Z' -> {
                    for (i in 0..25){
                        if (EN_A[i] == c) d = EN_A_new[i]
                    }
                    d
                }
                in 'А'..'Я' -> {
                    for (i in 0..31){
                        if (RU_A[i] == c) d = Ru_A_new[i]
                    }
                    d
                }
                in 'а'..'я' -> {
                    for (i in 0..31){
                        if (RU_a[i] == c) d = RU_a_new[i]
                    }
                    d
                /*    EN_a[c.toInt() - aByte_eng]
                }
                in 'A'..'Z' -> {
                    EN_A[c.toInt() - AByte_eng]
                }
                in 'А'..'Я' -> {
                    RU_A[c.toInt() - AByte_ru]
                }
                in 'а'..'я' -> {
                    RU_a[c.toInt() - aByte_ru]*/
                }
                else -> {
                    c
                }
            }

            append(ch)
        }
    }.toString()

    class Factory : Cipher.Factory {

        override val title = "Lbnmv cipher"

        override fun build(parameters: Map<String, Any>): LbnmvCipher {
            val key = (parameters["key"] as? String?)
                ?: throw IllegalAccessException("`key` parameter is required and should be String")
            var keyAr: Array<Int> = Array(key.length, { 0 })
            var i = 0
            for (c in key) {
                keyAr[i] = Integer.parseInt(c.toString())
                i++
            }
            return LbnmvCipher(keyAr.toIntArray())
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