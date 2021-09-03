package com.github.mathsemilio.stackoverflowquestions.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.github.mathsemilio.stackoverflowquestions.StackoverflowQuestionsApplication
import com.github.mathsemilio.stackoverflowquestions.common.di.ActivityCompositionRoot

abstract class BaseActivity : AppCompatActivity() {

    val compositionRoot by lazy {
        ActivityCompositionRoot(
            activity = this,
            (application as StackoverflowQuestionsApplication).compositionRoot
        )
    }
}