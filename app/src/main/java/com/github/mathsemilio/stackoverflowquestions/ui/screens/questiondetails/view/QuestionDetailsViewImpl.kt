package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question

class QuestionDetailsViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : QuestionDetailsView() {

    private var constraintLayoutQuestionDetailsErrorState: ConstraintLayout
    private val constraintLayoutQuestionDetailsContent: ConstraintLayout
    private var swipeRefreshLayoutQuestionDetails: SwipeRefreshLayout

    private lateinit var textViewTryAgain: TextView

    private lateinit var textViewQuestionTitle: TextView
    private lateinit var textViewQuestionAuthor: TextView
    private lateinit var textViewQuestionBody: TextView

    init {
        rootView = layoutInflater.inflate(R.layout.fragment_question_detail, parent, false)

        constraintLayoutQuestionDetailsErrorState =
            rootView.findViewById(R.id.constraint_layout_question_details_error_state)
        constraintLayoutQuestionDetailsContent =
            rootView.findViewById(R.id.constraint_layout_question_details_content)
        swipeRefreshLayoutQuestionDetails =
            rootView.findViewById(R.id.swipe_refresh_layout_questions_details)

        initializeQuestionDetailsContentViews()
        initializeQuestionDetailsErrorStateViews()

        swipeRefreshLayoutQuestionDetails.isEnabled = false
    }

    private fun initializeQuestionDetailsContentViews() {
        constraintLayoutQuestionDetailsContent.apply {
            textViewQuestionTitle = findViewById(R.id.text_view_question_title)
            textViewQuestionAuthor = findViewById(R.id.text_view_question_author)
            textViewQuestionBody = findViewById(R.id.text_view_question_body)
        }
    }

    private fun initializeQuestionDetailsErrorStateViews() {
        textViewTryAgain =
            constraintLayoutQuestionDetailsErrorState.findViewById(R.id.text_view_try_again)
    }

    override fun bindQuestion(question: Question) {
        textViewQuestionTitle.text = question.title
        textViewQuestionAuthor.text = context.getString(R.string.by_user, question.owner.displayName)
        textViewQuestionBody.text = Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun showProgressIndicator() {
        constraintLayoutQuestionDetailsContent.isVisible = false
        swipeRefreshLayoutQuestionDetails.isRefreshing = true
    }

    override fun hideProgressIndicator() {
        constraintLayoutQuestionDetailsContent.isVisible = true
        swipeRefreshLayoutQuestionDetails.isRefreshing = false
    }

    override fun showErrorState() {
        constraintLayoutQuestionDetailsErrorState.isVisible = true
        attachTryAgainButtonOnClickListener()
    }

    private fun attachTryAgainButtonOnClickListener() {
        textViewTryAgain.setOnClickListener {
            notifyListener { listener ->
                listener.onTryAgainButtonClicked()
            }
        }
    }

    override fun hideErrorState() {
        constraintLayoutQuestionDetailsErrorState.isVisible = false
    }
}