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