package com.makentoshe.chiperchan

interface ChipherchanSout {

    /** Prints message in a toast manner */
    fun toast(message: String)

    /** Prints message in a snackbar manner if feature is implemented on the current screen */
    fun snackbar(message: String)

    /** Prints message on ui if feature is implemented on the current screen */
    fun ui(message: String)
}