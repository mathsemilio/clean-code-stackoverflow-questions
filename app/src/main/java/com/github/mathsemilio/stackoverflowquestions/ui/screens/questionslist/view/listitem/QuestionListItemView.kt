package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.listitem

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.common.view.BaseObservableView

abstract class QuestionListItemView : BaseObservableView<QuestionListItemView.Listener>() {

    interface Listener {
        fun onQuestionClicked(questionId: String)
    }

    abstract fun bindQuestion(question: Question)
}