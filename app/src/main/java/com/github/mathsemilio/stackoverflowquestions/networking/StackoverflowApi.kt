package com.github.mathsemilio.stackoverflowquestions.networking

import com.github.mathsemilio.stackoverflowquestions.domain.model.response.question.QuestionsResponseSchema
import retrofit2.Response
import retrofit2.http.GET

interface StackoverflowApi {

    @GET("/2.3/questions?key=UvCy98LL0Z9RRyxlla47cg((&pagesize=30&order=desc&sort=activity&site=stackoverflow")
    suspend fun getLastActiveQuestions(): Response<QuestionsResponseSchema>
}