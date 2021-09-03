package com.github.mathsemilio.stackoverflowquestions.ui.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.github.mathsemilio.stackoverflowquestions.R
import com.google.android.material.appbar.MaterialToolbar

class MainActivityViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MainActivityView() {

    private var materialToolbarApp: MaterialToolbar
    private var frameLayoutFragmentContainer: FrameLayout

    init {
        rootView = layoutInflater.inflate(R.layout.activity_main, parent, false)

        materialToolbarApp = rootView.findViewById(R.id.material_toolbar_app)
        frameLayoutFragmentContainer = rootView.findViewById(R.id.frame_layout_fragment_container)

        attachToolbarNavigationIconOnClickListener()
    }

    private fun attachToolbarNavigationIconOnClickListener() {
        materialToolbarApp.setNavigationOnClickListener {
            notifyListener { listener ->
                listener.onToolbarNavigationIconClicked()
            }
        }
    }

    override val fragmentContainer: FrameLayout
        get() = frameLayoutFragmentContainer

    override fun setToolbarTitle(@StringRes titleId: Int) {
        materialToolbarApp.title = getString(titleId)
    }

    override fun showToolbarNavigationIcon() {
        materialToolbarApp.navigationIcon = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_arrow_back,
            null
        )
    }

    override fun hideToolbarNavigationIcon() {
        materialToolbarApp.navigationIcon = null
    }
}