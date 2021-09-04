package com.github.mathsemilio.stackoverflowquestions.domain.model.question

import com.github.mathsemilio.stackoverflowquestions.domain.model.user.StackexchangeUser
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(
    @SerializedName("question_id")
    val id: String,
    val title: String,
    val body: String,
    val owner: StackexchangeUser
) : Serializable