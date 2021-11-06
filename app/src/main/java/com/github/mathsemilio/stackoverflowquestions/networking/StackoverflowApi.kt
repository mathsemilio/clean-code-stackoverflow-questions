/*
Copyright 2021 Matheus Menezes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

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
