package com.github.mathsemilio.stackoverflowquestions.networking.endpoint

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.domain.model.result.Result
import com.github.mathsemilio.stackoverflowquestions.networking.api.StackoverflowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LastActiveQuestionsEndpoint(private val stackoverflowApi: StackoverflowApi) {

    suspend fun fetchLastActiveQuestions(): Result<List<Question>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.fetchLastActiveQuestions()
                Result.Completed(data = response.body()?.questions)
            } catch (exception: Exception) {
                Result.Failed(errorMessage = exception.message)
            }
        }
    }
}