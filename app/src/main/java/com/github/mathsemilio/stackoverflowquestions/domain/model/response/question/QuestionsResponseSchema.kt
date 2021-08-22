package com.github.mathsemilio.stackoverflowquestions.domain.model.response.question

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.google.gson.annotations.SerializedName

data class QuestionsResponseSchema(
    @SerializedName("items")
    val questions: List<Question>
)