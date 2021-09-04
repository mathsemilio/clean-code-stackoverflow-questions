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