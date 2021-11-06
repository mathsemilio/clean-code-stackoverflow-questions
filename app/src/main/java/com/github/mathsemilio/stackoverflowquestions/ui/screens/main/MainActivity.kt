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

package com.github.mathsemilio.stackoverflowquestions.ui.screens.main

import android.os.Bundle
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.ui.common.BaseActivity
import com.github.mathsemilio.stackoverflowquestions.ui.common.manager.FragmentContainerManager
import com.github.mathsemilio.stackoverflowquestions.ui.common.navigation.NavigationEventListener
import com.github.mathsemilio.stackoverflowquestions.ui.common.navigation.ScreensNavigator
import com.github.mathsemilio.stackoverflowquestions.ui.screens.main.view.MainActivityView
import com.github.mathsemilio.stackoverflowquestions.ui.screens.main.view.MainActivityViewImpl

class MainActivity : BaseActivity(),
    MainActivityView.Listener,
    FragmentContainerManager,
    NavigationEventListener {

    private lateinit var view: MainActivityView
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screensNavigator = compositionRoot.screensNavigator
        view = MainActivityViewImpl(layoutInflater, parent = null)

        setContentView(view.rootView)

        if (savedInstanceState == null)
            screensNavigator.toQuestionListScreen()
    }

    override val fragmentContainerId get() = view.fragmentContainer.id

    override fun onToolbarNavigationIconClicked() {
        screensNavigator.navigateUp()
    }

    override fun onNavigateToSecondaryDestination() {
        view.showToolbarNavigationIcon()
        view.setToolbarTitle(R.string.toolbar_title_question_details)
    }

    override fun onNavigateUpEvent() {
        view.hideToolbarNavigationIcon()
        view.setToolbarTitle(R.string.toolbar_title_last_active_questions)
    }

    override fun onStart() {
        super.onStart()
        view.addListener(this)
    }

    override fun onStop() {
        super.onStop()
        view.removeListener(this)
    }
}
