package com.github.mathsemilio.stackoverflowquestions.common.di

import com.github.mathsemilio.stackoverflowquestions.common.BASE_URL
import com.github.mathsemilio.stackoverflowquestions.common.provider.CoroutineScopeProvider
import com.github.mathsemilio.stackoverflowquestions.networking.api.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stackoverflowApi: StackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }

    val coroutineScopeProvider
        get() = CoroutineScopeProvider
}