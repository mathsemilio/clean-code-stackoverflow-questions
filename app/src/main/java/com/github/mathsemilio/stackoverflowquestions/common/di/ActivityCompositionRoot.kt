package com.github.mathsemilio.stackoverflowquestions.common.di

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.github.mathsemilio.stackoverflowquestions.domain.usecase.question.FetchLastActiveQuestionsUseCase
import com.github.mathsemilio.stackoverflowquestions.networking.endpoint.LastActiveQuestionsEndpoint
import com.github.mathsemilio.stackoverflowquestions.ui.common.view.ViewFactory

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val compositionRoot: CompositionRoot
) {
    private val lastActiveQuestionsEndpoint
        get() = LastActiveQuestionsEndpoint(compositionRoot.stackoverflowApi)

    val fetchLastActiveQuestionsUseCase
        get() = FetchLastActiveQuestionsUseCase(lastActiveQuestionsEndpoint)

    val coroutineScopeProvider
        get() = compositionRoot.coroutineScopeProvider

    val viewFactory
        get() = ViewFactory(LayoutInflater.from(compositionRoot.application))
}