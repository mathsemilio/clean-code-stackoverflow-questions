package com.github.mathsemilio.stackoverflowquestions.networking.api

import com.github.mathsemilio.stackoverflowquestions.common.API_KEY
import com.github.mathsemilio.stackoverflowquestions.domain.model.response.question.QuestionsResponseSchema
import com.github.mathsemilio.stackoverflowquestions.domain.model.response.questiondetail.QuestionDetailsResponseSchema
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StackoverflowApi {

    @GET("questions?key=$API_KEY&pagesize=30&order=desc&sort=activity&site=stackoverflow")
    suspend fun fetchLastActiveQuestions(): Response<QuestionsResponseSchema>

    @GET("questions/{questionId}?key=$API_KEY&site=stackoverflow&filter=withbody")
    suspend fun fetchQuestionDetails(
        @Path("questionId") questionId: String
    ): Response<QuestionDetailsResponseSchema>
}