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

package com.github.mathsemilio.stackoverflowquestions.common.di

import androidx.appcompat.app.AppCompatActivity
import com.github.mathsemilio.stackoverflowquestions.networking.endpoint.LastActiveQuestionsEndpoint
import com.github.mathsemilio.stackoverflowquestions.ui.common.manager.FragmentContainerManager
import com.github.mathsemilio.stackoverflowquestions.ui.common.manager.FragmentTransactionManager
import com.github.mathsemilio.stackoverflowquestions.ui.common.navigation.NavigationEventListener
import com.github.mathsemilio.stackoverflowquestions.ui.common.navigation.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val compositionRoot: CompositionRoot
) {
    private val fragmentTransactionManager
        get() = FragmentTransactionManager(
            activity.supportFragmentManager,
            activity as FragmentContainerManager
        )

    val coroutineScopeProvider
        get() = compositionRoot.coroutineScopeProvider

    val lastActiveQuestionsEndpoint
        get() = LastActiveQuestionsEndpoint(compositionRoot.stackoverflowApi)

    val screensNavigator
        get() = ScreensNavigator(fragmentTransactionManager, activity as NavigationEventListener)
}