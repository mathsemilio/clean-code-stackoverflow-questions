package com.github.mathsemilio.stackoverflowquestions.common.di

import android.app.Application
import com.github.mathsemilio.stackoverflowquestions.common.provider.CoroutineScopeProvider
import com.github.mathsemilio.stackoverflowquestions.networking.api.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot(val application: Application) {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.stackexchange.com/2.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stackoverflowApi: StackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }

    val coroutineScopeProvider
        get() = CoroutineScopeProvider
}