package com.makentoshe.chiperchan.view.cipher

import java.io.Serializable

sealed class Action : Serializable {
    object Encode : Action(), Serializable
    object Decode : Action(), Serializable
}