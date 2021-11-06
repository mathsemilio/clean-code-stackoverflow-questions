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
