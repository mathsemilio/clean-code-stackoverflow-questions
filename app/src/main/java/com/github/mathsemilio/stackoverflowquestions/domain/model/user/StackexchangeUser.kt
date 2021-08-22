package com.github.mathsemilio.stackoverflowquestions.domain.model.user

import com.google.gson.annotations.SerializedName

data class StackexchangeUser(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("profile_image")
    val profileImageUrl: String
)