package com.github.mathsemilio.stackoverflowquestions.networking.api

import com.github.mathsemilio.stackoverflowquestions.common.API_KEY
import com.github.mathsemilio.stackoverflowquestions.domain.model.response.question.QuestionsResponseSchema
import retrofit2.Response
import retrofit2.http.GET

interface StackoverflowApi {

    @GET("questions?key=$API_KEY&pagesize=30&order=desc&sort=activity&site=stackoverflow&filter=withbody")
    suspend fun fetchLastActiveQuestions(): Response<QuestionsResponseSchema>
}