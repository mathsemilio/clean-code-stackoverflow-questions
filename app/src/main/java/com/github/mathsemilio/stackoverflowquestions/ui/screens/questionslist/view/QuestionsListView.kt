package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.common.view.BaseObservableView

abstract class QuestionsListView : BaseObservableView<QuestionsListView.Listener>() {

    interface Listener {
        fun onQuestionClicked(questionId: String)

        fun onScreenSwipedToRefresh()
    }

    abstract fun bindQuestions(questions: List<Question>)

    abstract fun showProgressIndicator()

    abstract fun hideProgressIndicator()

    abstract fun showErrorState()

    abstract fun hideErrorState()
}