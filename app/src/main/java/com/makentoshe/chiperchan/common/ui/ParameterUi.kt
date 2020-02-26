package com.makentoshe.chiperchan.common.ui

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.textfield.TextInputLayout
import com.makentoshe.chiperchan.R
import com.makentoshe.chiperchan.model.Cipher

class ParameterUi(private val root: ViewGroup?) {

    fun createView(context: Context, parameter: Cipher.Parameter) = when (parameter.spec.type) {
        Cipher.Type.String -> createViewString(context, parameter)
        Cipher.Type.Int -> createViewInt(context, parameter)
        Cipher.Type.Boolean -> createViewBool(context, parameter)
    }

    private fun createViewString(context: Context, parameter: Cipher.Parameter): TextInputLayout {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_string, root, false) as TextInputLayout
        view.id = parameter.viewId
        view.hint = parameter.displayName
        return view
    }

    private fun createViewInt(context: Context, parameter: Cipher.Parameter): View {
        val view = createViewString(context, parameter)
        view.editText?.inputType = InputType.TYPE_CLASS_NUMBER
        view.hint = parameter.displayName
        return view
    }

    private fun createViewBool(context: Context, parameter: Cipher.Parameter): View {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_boolean, root, false) as ViewGroup
        view.children.filterIsInstance<TextView>().first().text = parameter.displayName
        view.id = parameter.viewId
        return view
    }
}