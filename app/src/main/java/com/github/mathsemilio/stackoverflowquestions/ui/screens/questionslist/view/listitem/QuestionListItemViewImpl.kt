package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.listitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question

class QuestionListItemViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : QuestionListItemView() {

    private var textViewQuestionTitle: TextView

    private lateinit var question: Question

    init {
        rootView = layoutInflater.inflate(R.layout.question_list_item, parent, false)
        textViewQuestionTitle = rootView.findViewById(R.id.text_view_question_title)
        setQuestionListItemOnClickListener()
    }

    private fun setQuestionListItemOnClickListener() {
        rootView.setOnClickListener {
            notifyListener { listener ->
                listener.onQuestionClicked(question.id)
            }
        }
    }

    override fun bindQuestion(question: Question) {
        this.question = question
        textViewQuestionTitle.text = question.title
    }
}