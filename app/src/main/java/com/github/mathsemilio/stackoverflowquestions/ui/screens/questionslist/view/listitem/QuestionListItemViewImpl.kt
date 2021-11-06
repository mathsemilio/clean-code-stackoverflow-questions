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
                listener.onQuestionClicked(question)
            }
        }
    }

    override fun bindQuestion(question: Question) {
        this.question = question
        textViewQuestionTitle.text = question.title
    }
}
