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

package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question

class QuestionDetailsViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : QuestionDetailsView() {

    private var textViewQuestionTitle: TextView
    private var textViewQuestionAuthor: TextView
    private var textViewQuestionBody: TextView

    init {
        rootView = layoutInflater.inflate(R.layout.fragment_question_detail, parent, false)

        textViewQuestionTitle = rootView.findViewById(R.id.text_view_question_title)
        textViewQuestionAuthor = rootView.findViewById(R.id.text_view_question_author)
        textViewQuestionBody = rootView.findViewById(R.id.text_view_question_body)
    }

    override fun bindQuestion(question: Question) {
        textViewQuestionTitle.text = question.title
        textViewQuestionAuthor.text = context.getString(R.string.by_user, question.owner.displayName)
        textViewQuestionBody.text = Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY)
    }
}
