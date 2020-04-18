package com.makentoshe.chiperchan.common.ui

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.makentoshe.chiperchan.R
import com.makentoshe.chiperchan.model.cipher.Cipher

class ParameterUi(private val root: ViewGroup?) {

    fun createView(context: Context, parameter: Cipher.Parameter, action: () -> Unit) = when (parameter.spec.type) {
        Cipher.Type.String -> createViewString(context, parameter, action)
        Cipher.Type.Int -> createViewInt(context, parameter, action)
        Cipher.Type.Boolean -> createViewBool(context, parameter, action)
        Cipher.Type.Plain -> createViewPlain(context, parameter, action)
    }

    private fun createViewString(context: Context, parameter: Cipher.Parameter, action: () -> Unit): TextInputLayout {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_string, root, false) as TextInputLayout
        view.hint = parameter.displayName
        view.tag = parameter.name
        view.editText?.doAfterTextChanged { action() }
        return view
    }

    private fun createViewInt(context: Context, parameter: Cipher.Parameter, action: () -> Unit): View {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_string, root, false) as TextInputLayout
        view.hint = parameter.displayName
        view.tag = parameter.name
        view.editText?.doAfterTextChanged { action() }
        view.editText?.inputType = InputType.TYPE_CLASS_NUMBER
        return view
    }

    private fun createViewBool(context: Context, parameter: Cipher.Parameter, action: () -> Unit): View {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_boolean, root, false) as ViewGroup
        view.children.filterIsInstance<TextView>().first().text = parameter.displayName
        view.children.filterIsInstance<CheckBox>().first().setOnCheckedChangeListener { _, _ -> action() }
        view.tag = parameter.name
        return view
    }

    private fun createViewPlain(context: Context, parameter: Cipher.Parameter, action: () -> Unit): View {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_plain, root, false) as TextInputLayout
        view.hint = parameter.displayName
        view.tag = parameter.name
        return view
    }
}