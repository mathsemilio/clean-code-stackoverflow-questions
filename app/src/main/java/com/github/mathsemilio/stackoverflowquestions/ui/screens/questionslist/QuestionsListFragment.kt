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

package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.usecase.question.FetchLastActiveQuestionsUseCase
import com.github.mathsemilio.stackoverflowquestions.ui.common.BaseFragment
import com.github.mathsemilio.stackoverflowquestions.ui.common.navigation.ScreensNavigator
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.QuestionsListView
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.QuestionsListViewImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QuestionsListFragment : BaseFragment(),
    QuestionsListView.Listener,
    FetchLastActiveQuestionsUseCase.Listener {

    companion object {
        fun newInstance() = QuestionsListFragment()
    }

    private lateinit var view: QuestionsListView

    private lateinit var screensNavigator: ScreensNavigator
    private lateinit var coroutineScope: CoroutineScope

    private lateinit var fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screensNavigator = compositionRoot.screensNavigator
        coroutineScope = compositionRoot.coroutineScopeProvider.UIBoundScope
        fetchLastActiveQuestionsUseCase = compositionRoot.fetchLastActiveQuestionsUseCase
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = QuestionsListViewImpl(layoutInflater, null)
        return view.rootView
    }

    override fun onLastActiveQuestionsFetchedSuccessfully(questions: List<Question>) {
        view.hideErrorState()
        view.hideProgressIndicator()
        view.bindQuestions(questions)
    }

    override fun onLastActiveQuestionFetchFailed() {
        view.hideProgressIndicator()
        view.showErrorState()
    }

    override fun onQuestionClicked(question: Question) {
        screensNavigator.toQuestionDetailsScreen(question)
    }

    override fun onScreenSwipedToRefresh() {
        fetchLastActiveQuestions()
    }

    private fun fetchLastActiveQuestions() {
        coroutineScope.launch {
            view.showProgressIndicator()
            fetchLastActiveQuestionsUseCase.fetchLastActiveQuestions()
        }
    }

    override fun onStart() {
        super.onStart()
        view.addListener(this)
        fetchLastActiveQuestionsUseCase.addListener(this)
        fetchLastActiveQuestions()
    }

    override fun onStop() {
        super.onStop()
        view.removeListener(this)
        fetchLastActiveQuestionsUseCase.removeListener(this)
    }
}
