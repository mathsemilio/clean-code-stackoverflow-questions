package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.common.view.BaseView

abstract class QuestionDetailsView : BaseView() {

    abstract fun bindQuestion(question: Question)
}