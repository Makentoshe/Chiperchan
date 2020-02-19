package com.makentoshe.chiperchan.view.cipher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makentoshe.chiperchan.model.Cipher
import com.makentoshe.chiperchan.ui.cipher.CipherFragmentUi
import toothpick.ktp.delegate.inject

class CipherFragment : Fragment() {

    val arguments = Arguments(this)
    private val cipherFactory by inject<Cipher.Factory>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return CipherFragmentUi(cipherFactory).create(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println(cipherFactory.title)
    }

    class Factory {
        fun build(cipherTitle: String): CipherFragment {
            val fragment = CipherFragment()
            fragment.arguments.cipherTitle = cipherTitle
            return fragment
        }
    }

    class Arguments(fragment: CipherFragment) {

        init {
            (fragment as Fragment).arguments = Bundle()
        }

        private val fragmentArguments = fragment.requireArguments()

        var cipherTitle: String?
            set(value) = fragmentArguments.putString(TITLE, value)
            get() = fragmentArguments.getString(TITLE)

        companion object {
            private const val TITLE = "Title"
        }
    }
}
