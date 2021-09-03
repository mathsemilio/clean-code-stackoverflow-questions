package com.github.mathsemilio.stackoverflowquestions.ui.common.view

import android.content.Context
import androidx.annotation.StringRes

abstract class BaseView : View {

    private lateinit var _rootView: android.view.View

    override var rootView: android.view.View
        get() = _rootView
        set(value) {
            _rootView = value
        }

    protected fun <T : View> findViewById(id: Int): T = _rootView.findViewById(id)

    protected val context: Context get() = _rootView.context

    protected fun getString(@StringRes id: Int) = context.getString(id)
}