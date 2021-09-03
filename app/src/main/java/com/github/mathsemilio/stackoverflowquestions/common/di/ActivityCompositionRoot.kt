package com.github.mathsemilio.stackoverflowquestions.common.di

import androidx.appcompat.app.AppCompatActivity
import com.github.mathsemilio.stackoverflowquestions.domain.usecase.question.FetchLastActiveQuestionsUseCase
import com.github.mathsemilio.stackoverflowquestions.domain.usecase.questiondetails.FetchQuestionDetailsUseCase
import com.github.mathsemilio.stackoverflowquestions.networking.endpoint.LastActiveQuestionsEndpoint
import com.github.mathsemilio.stackoverflowquestions.networking.endpoint.QuestionDetailsEndpoint
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

    private val lastActiveQuestionsEndpoint
        get() = LastActiveQuestionsEndpoint(compositionRoot.stackoverflowApi)

    private val questionDetailsEndpoint
        get() = QuestionDetailsEndpoint(compositionRoot.stackoverflowApi)

    val coroutineScopeProvider
        get() = compositionRoot.coroutineScopeProvider

    val screensNavigator
        get() = ScreensNavigator(fragmentTransactionManager, activity as NavigationEventListener)

    val fetchLastActiveQuestionsUseCase
        get() = FetchLastActiveQuestionsUseCase(lastActiveQuestionsEndpoint)

    val fetchQuestionDetailsUseCase
        get() = FetchQuestionDetailsUseCase(questionDetailsEndpoint)
}