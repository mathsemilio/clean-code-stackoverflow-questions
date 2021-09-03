package com.github.mathsemilio.stackoverflowquestions.ui.common.navigation

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

    fun toQuestionDetailsScreen(questionId: String) {
        fragmentTransactionManager.pushFragmentOntoContainer(
            QuestionDetailsFragment.withQuestionId(questionId),
            stackEntryName = null
        )
        navigationEventListener.onNavigateToSecondaryDestination()
    }

    fun navigateUp() {
        fragmentTransactionManager.popCurrentFragment()
        navigationEventListener.onNavigateUpEvent()
    }
}