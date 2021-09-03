package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.common.view.BaseObservableView

abstract class QuestionDetailsView : BaseObservableView<QuestionDetailsView.Listener>() {

    interface Listener {
        fun onTryAgainButtonClicked()
    }

    abstract fun bindQuestion(question: Question)

    abstract fun showProgressIndicator()

    abstract fun hideProgressIndicator()

    abstract fun showErrorState()

    abstract fun hideErrorState()
}