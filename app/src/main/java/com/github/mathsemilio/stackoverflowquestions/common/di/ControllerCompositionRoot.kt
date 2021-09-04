package com.github.mathsemilio.stackoverflowquestions.common.di

import com.github.mathsemilio.stackoverflowquestions.domain.usecase.question.FetchLastActiveQuestionsUseCase

class ControllerCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    val coroutineScopeProvider
        get() = activityCompositionRoot.coroutineScopeProvider

    val fetchLastActiveQuestionsUseCase
        get() = FetchLastActiveQuestionsUseCase(activityCompositionRoot.lastActiveQuestionsEndpoint)

    val screensNavigator
        get() = activityCompositionRoot.screensNavigator
}