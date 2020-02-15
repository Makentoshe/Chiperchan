package com.makentoshe.chiperchan.common.navigator

import androidx.fragment.app.Fragment

/**
 * Class for boxing fragment into [Screen] instance
 */
abstract class Screen : ru.terrakok.cicerone.Screen() {
    abstract val fragment: Fragment
}