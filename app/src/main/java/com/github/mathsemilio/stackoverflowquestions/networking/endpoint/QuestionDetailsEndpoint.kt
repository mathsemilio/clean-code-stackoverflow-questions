package com.github.mathsemilio.stackoverflowquestions.networking.endpoint

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.model.result.Result
import com.github.mathsemilio.stackoverflowquestions.networking.api.StackoverflowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionDetailsEndpoint(private val stackoverflowApi: StackoverflowApi) {

    suspend fun fetchQuestionDetailWith(questionId: String): Result<Question> {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.fetchQuestionDetails(questionId)
                Result.Completed(data = response.body()?.questions?.get(0))
            } catch (exception: Exception) {
                Result.Failed(errorMessage = exception.message)
            }
        }
    }
}