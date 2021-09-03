package com.github.mathsemilio.stackoverflowquestions.ui.common.view

import android.view.LayoutInflater
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.QuestionsListViewImpl

class ViewFactory(private val layoutInflater: LayoutInflater) {

    val questionsListView
        get() = QuestionsListViewImpl(layoutInflater, null)
}