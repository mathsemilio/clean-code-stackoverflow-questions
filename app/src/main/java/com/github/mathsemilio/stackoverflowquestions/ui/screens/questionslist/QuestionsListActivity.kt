package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist

import android.os.Bundle
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.usecase.question.FetchLastActiveQuestionsUseCase
import com.github.mathsemilio.stackoverflowquestions.ui.common.BaseActivity
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.QuestionDetailActivity
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.QuestionsListView
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.QuestionsListViewImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QuestionsListActivity : BaseActivity(),
    QuestionsListView.Listener,
    FetchLastActiveQuestionsUseCase.Listener {

    private lateinit var view: QuestionsListView

    private lateinit var coroutineScope: CoroutineScope

    private lateinit var fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coroutineScope = compositionRoot.coroutineScopeProvider.UIBoundScope
        fetchLastActiveQuestionsUseCase = compositionRoot.fetchLastActiveQuestionsUseCase

        view = QuestionsListViewImpl(layoutInflater, null)

        setContentView(view.rootView)

        supportActionBar?.title = getString(R.string.toolbar_title_last_active_questions)
    }

    override fun onLastActiveQuestionsFetchedSuccessfully(questions: List<Question>) {
        view.hideErrorState()
        view.hideProgressIndicator()
        view.bindQuestions(questions)
    }

    override fun onLastActiveQuestionFetchFailed() {
        view.hideProgressIndicator()
        view.showErrorState()
    }

    override fun onQuestionClicked(questionId: String) {
        val intent = QuestionDetailActivity.withQuestionId(this, questionId)
        startActivity(intent)
    }

    override fun onTryAgainButtonClicked() = fetchLastActiveQuestions()

    private fun fetchLastActiveQuestions() {
        coroutineScope.launch {
            view.showProgressIndicator()
            fetchLastActiveQuestionsUseCase.fetchLastActiveQuestions()
        }
    }

    override fun onStart() {
        super.onStart()
        view.addListener(this)
        fetchLastActiveQuestionsUseCase.addListener(this)
        fetchLastActiveQuestions()
    }

    override fun onStop() {
        super.onStop()
        view.removeListener(this)
        fetchLastActiveQuestionsUseCase.removeListener(this)
    }
}