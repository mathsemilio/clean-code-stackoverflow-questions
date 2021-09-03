package com.github.mathsemilio.stackoverflowquestions.domain.usecase.question

import com.github.mathsemilio.stackoverflowquestions.common.baseobservable.BaseObservable
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.model.result.Result
import com.github.mathsemilio.stackoverflowquestions.networking.endpoint.LastActiveQuestionsEndpoint

class FetchLastActiveQuestionsUseCase(
    private val endpoint: LastActiveQuestionsEndpoint
) : BaseObservable<FetchLastActiveQuestionsUseCase.Listener>() {

    interface Listener {
        fun onLastActiveQuestionsFetchedSuccessfully(questions: List<Question>)

        fun onLastActiveQuestionFetchFailed()
    }

    suspend fun fetchLastActiveQuestions() {
        endpoint.fetchLastActiveQuestions().also { result ->
            when (result) {
                is Result.Completed -> notifyListener { listener ->
                    listener.onLastActiveQuestionsFetchedSuccessfully(questions = result.data!!)
                }
                is Result.Failed -> notifyListener { listener ->
                    listener.onLastActiveQuestionFetchFailed()
                }
            }
        }
    }
}