package com.makentoshe.chiperchan.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.makentoshe.chiperchan.di.cipher.CipherFragmentModule
import com.makentoshe.chiperchan.di.cipher.CipherFragmentScope
import com.makentoshe.chiperchan.di.common.ApplicationScope
import com.makentoshe.chiperchan.di.main.MainFragmentModule
import com.makentoshe.chiperchan.di.main.MainFragmentScope
import com.makentoshe.chiperchan.view.cipher.CipherFragment
import com.makentoshe.chiperchan.view.main.MainFragment
import toothpick.Toothpick
import toothpick.smoothie.lifecycle.closeOnDestroy

class InjectionFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) = when (f) {
        is MainFragment -> injectMainFragment(f)
        is CipherFragment -> injectCipherFragment(f)
        else -> Unit
    }

    private fun injectMainFragment(fragment: MainFragment) {
        val module = MainFragmentModule()
        val scope = Toothpick.openScopes(ApplicationScope::class.java, MainFragmentScope::class.java)
        scope.installModules(module).closeOnDestroy(fragment).inject(fragment)
    }

    private fun injectCipherFragment(fragment: CipherFragment) {
        val module = CipherFragmentModule(fragment)
        val scope = Toothpick.openScopes(ApplicationScope::class.java, CipherFragmentScope::class.java)
        scope.installModules(module).closeOnDestroy(fragment).inject(fragment)
    }
}