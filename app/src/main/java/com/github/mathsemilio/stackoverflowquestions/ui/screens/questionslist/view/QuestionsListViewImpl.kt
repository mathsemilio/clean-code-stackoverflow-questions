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

package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.QuestionsListAdapter

class QuestionsListViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : QuestionsListView(), QuestionsListAdapter.OnQuestionClickListener {

    private var constraintLayoutQuestionsListErrorState: ConstraintLayout
    private var swipeRefreshLayoutQuestionsList: SwipeRefreshLayout
    private var recyclerViewQuestionsList: RecyclerView

    init {
        rootView = layoutInflater.inflate(R.layout.fragment_questions_list, parent, false)

        constraintLayoutQuestionsListErrorState =
            rootView.findViewById(R.id.constraint_layout_question_list_error_state)
        swipeRefreshLayoutQuestionsList =
            rootView.findViewById(R.id.swipe_refresh_layout_questions_list)
        recyclerViewQuestionsList =
            rootView.findViewById(R.id.recycler_view_questions_list)

        attachSwipeRefreshLayoutOnRefreshListener()
    }

    private fun attachSwipeRefreshLayoutOnRefreshListener() {
        swipeRefreshLayoutQuestionsList.setOnRefreshListener {
            notifyListener { listener ->
                listener.onScreenSwipedToRefresh()
            }
        }
    }

    override fun bindQuestions(questions: List<Question>) {
        val questionsListAdapter = QuestionsListAdapter(questions, this)
        recyclerViewQuestionsList.apply {
            adapter = questionsListAdapter
            setHasFixedSize(true)
        }
    }

    override fun showProgressIndicator() {
        recyclerViewQuestionsList.isVisible = false
        swipeRefreshLayoutQuestionsList.isRefreshing = true
    }

    override fun hideProgressIndicator() {
        recyclerViewQuestionsList.isVisible = true
        swipeRefreshLayoutQuestionsList.isRefreshing = false
    }

    override fun showErrorState() {
        constraintLayoutQuestionsListErrorState.isVisible = true
        recyclerViewQuestionsList.isVisible = false
    }

    override fun hideErrorState() {
        constraintLayoutQuestionsListErrorState.isVisible = false
        recyclerViewQuestionsList.isVisible = true
    }

    override fun onQuestionClicked(question: Question) {
        notifyListener { listener ->
            listener.onQuestionClicked(question)
        }
    }
}
