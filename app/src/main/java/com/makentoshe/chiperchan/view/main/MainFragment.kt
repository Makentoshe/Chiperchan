package com.makentoshe.chiperchan.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.makentoshe.chiperchan.R
import com.makentoshe.chiperchan.model.Cipher
import com.makentoshe.chiperchan.model.cipher.CipherScreen
import com.makentoshe.chiperchan.ui.main.MainFragmentUi
import ru.terrakok.cicerone.Router
import toothpick.ktp.delegate.inject

class MainFragment : Fragment() {

    private val navigator by inject<Navigator>()
    private val cipherFactoryList by inject<List<Cipher.Factory>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return MainFragmentUi().create(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listView = view.findViewById<ListView>(R.id.main_fragment_listview)
        val source = cipherFactoryList.map { it.title }
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, source)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            navigator.toCipherScreen(cipherFactoryList[position].title)
        }
    }

    class Factory {
        fun build(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

    class Navigator(private val router: Router) {
        fun toCipherScreen(cipherTitle: String) {
            router.navigateTo(CipherScreen(cipherTitle))
        }
    }
}
