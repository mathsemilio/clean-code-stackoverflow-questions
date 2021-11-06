/*
Copyright 2021 Matheus Menezes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.github.mathsemilio.stackoverflowquestions.ui.screens.main.view

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
