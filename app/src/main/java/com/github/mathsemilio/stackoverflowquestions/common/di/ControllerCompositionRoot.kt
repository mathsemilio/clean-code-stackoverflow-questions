package com.github.mathsemilio.stackoverflowquestions.common.di

class ControllerCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    val coroutineScopeProvider
        get() = activityCompositionRoot.coroutineScopeProvider

    val screensNavigator
        get() = activityCompositionRoot.screensNavigator

    val fetchLastActiveQuestionsUseCase
        get() = activityCompositionRoot.fetchLastActiveQuestionsUseCase

    val fetchQuestionDetailsUseCase
        get() = activityCompositionRoot.fetchQuestionDetailsUseCase
}