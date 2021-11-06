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

package com.github.mathsemilio.stackoverflowquestions.ui.common.navigation

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.common.manager.FragmentTransactionManager
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.QuestionDetailsFragment
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.QuestionsListFragment

class ScreensNavigator(
    private val fragmentTransactionManager: FragmentTransactionManager,
    private val navigationEventListener: NavigationEventListener
) {
    fun toQuestionListScreen() {
        fragmentTransactionManager.replaceFragmentInContainerWith(
            QuestionsListFragment.newInstance()
        )
    }

    fun toQuestionDetailsScreen(question: Question) {
        fragmentTransactionManager.pushFragmentOntoContainer(
            QuestionDetailsFragment.withQuestion(question),
            stackEntryName = null
        )
        navigationEventListener.onNavigateToSecondaryDestination()
    }

    fun navigateUp() {
        fragmentTransactionManager.popCurrentFragment()
        navigationEventListener.onNavigateUpEvent()
    }
}
