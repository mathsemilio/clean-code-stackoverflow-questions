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

    override fun onQuestionClicked(questionId: String) {
        notifyListener { listener ->
            listener.onQuestionClicked(questionId)
        }
    }
}