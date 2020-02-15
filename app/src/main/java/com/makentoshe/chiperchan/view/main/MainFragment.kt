package com.makentoshe.chiperchan.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.makentoshe.chiperchan.model.Cipher
import toothpick.ktp.delegate.inject

class MainFragment : Fragment() {

    private val cipherList by inject<List<Cipher>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(requireContext()).apply {
            text = cipherList.toString()
        }
    }

    class Factory {
        fun build(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }
}
