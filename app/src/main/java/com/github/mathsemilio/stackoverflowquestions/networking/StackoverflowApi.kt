package com.github.mathsemilio.stackoverflowquestions.networking

import com.github.mathsemilio.stackoverflowquestions.domain.model.response.question.QuestionsResponseSchema
import com.github.mathsemilio.stackoverflowquestions.domain.model.response.questiondetail.QuestionDetailsResponseSchema
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StackoverflowApi {

    @GET("questions?key=UvCy98LL0Z9RRyxlla47cg((&pagesize=30&order=desc&sort=activity&site=stackoverflow")
    suspend fun getLastActiveQuestions(): Response<QuestionsResponseSchema>

    @GET("questions/{questionId}?key=UvCy98LL0Z9RRyxlla47cg((&site=stackoverflow&filter=withbody")
    suspend fun getQuestionDetails(
        @Path("questionId") questionId: String
    ): Response<QuestionDetailsResponseSchema>
}