package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mathsemilio.stackoverflowquestions.common.ARG_QUESTION_ID
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.usecase.questiondetails.FetchQuestionDetailsUseCase
import com.github.mathsemilio.stackoverflowquestions.ui.common.BaseFragment
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view.QuestionDetailsView
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.view.QuestionDetailsViewImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QuestionDetailsFragment : BaseFragment(),
    QuestionDetailsView.Listener,
    FetchQuestionDetailsUseCase.Listener {

    companion object {
        fun withQuestionId(questionId: String) = QuestionDetailsFragment().apply {
            arguments = Bundle(1).apply {
                putString(ARG_QUESTION_ID, questionId)
            }
        }
    }

    private lateinit var view: QuestionDetailsView

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    private lateinit var coroutineScope: CoroutineScope

    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coroutineScope = compositionRoot.coroutineScopeProvider.UIBoundScope
        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
    }

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
        getQuestionId()
    }

    private fun getQuestionId() {
        questionId = requireArguments().getString(ARG_QUESTION_ID, "")
    }

    override fun onScreenSwipedToRefresh() {
        getQuestionDetails()
    }

    private fun getQuestionDetails() {
        coroutineScope.launch {
            view.showProgressIndicator()
            fetchQuestionDetailsUseCase.fetchQuestionDetailsWith(questionId)
        }
    }

    override fun onQuestionDetailsFetchedSuccessfully(question: Question) {
        view.hideProgressIndicator()
        view.hideErrorState()
        view.bindQuestion(question)
    }

    override fun onFetchQuestionDetailsFailed() {
        view.hideProgressIndicator()
        view.showErrorState()
    }

    override fun onStart() {
        super.onStart()
        view.addListener(this)
        fetchQuestionDetailsUseCase.addListener(this)
        getQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        view.removeListener(this)
        fetchQuestionDetailsUseCase.removeListener(this)
    }
}