package com.github.mathsemilio.stackoverflowquestions.domain.model.response.questiondetail

import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.google.gson.annotations.SerializedName

data class QuestionDetailsResponseSchema(
    @SerializedName("items")
    val questions: List<Question>
)