package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mathsemilio.stackoverflowquestions.common.ARG_QUESTION
import com.github.mathsemilio.stackoverflowquestions.common.OUT_STATE_QUESTION
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.common.BaseFragment
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view.QuestionDetailsView
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view.QuestionDetailsViewImpl

class QuestionDetailsFragment : BaseFragment() {

    companion object {
        fun withQuestion(question: Question) = QuestionDetailsFragment().apply {
            arguments = Bundle(1).apply {
                putSerializable(ARG_QUESTION, question)
            }
        }
    }

    private lateinit var view: QuestionDetailsView

    private lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = QuestionDetailsViewImpl(layoutInflater, parent = null)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null)
            question = savedInstanceState.getSerializable(OUT_STATE_QUESTION) as Question
        else
            getQuestionFromBundle()
    }

    private fun getQuestionFromBundle() {
        question = requireArguments().getSerializable(ARG_QUESTION) as Question
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(OUT_STATE_QUESTION, question)
    }

    override fun onStart() {
        super.onStart()
        view.bindQuestion(question)
    }
}