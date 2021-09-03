package com.github.mathsemilio.stackoverflowquestions.domain.usecase.questiondetails

import com.github.mathsemilio.stackoverflowquestions.common.baseobservable.BaseObservable
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.model.result.Result
import com.github.mathsemilio.stackoverflowquestions.networking.endpoint.QuestionDetailsEndpoint

class FetchQuestionDetailsUseCase(
    private val endpoint: QuestionDetailsEndpoint
) : BaseObservable<FetchQuestionDetailsUseCase.Listener>() {

    interface Listener {
        fun onQuestionDetailsFetchedSuccessfully(question: Question)

        fun onFetchQuestionDetailsFailed()
    }

    suspend fun fetchQuestionDetailsWith(questionId: String) {
        endpoint.fetchQuestionDetailWith(questionId).also { result ->
            when (result) {
                is Result.Completed -> notifyListener { listener ->
                    listener.onQuestionDetailsFetchedSuccessfully(question = result.data!!)
                }
                is Result.Failed -> notifyListener { listener ->
                    listener.onFetchQuestionDetailsFailed()
                }
            }
        }
    }
}