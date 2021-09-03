package com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.common.ARG_QUESTION_ID
import com.github.mathsemilio.stackoverflowquestions.networking.api.StackoverflowApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionDetailActivity : AppCompatActivity() {

    companion object {
        fun withQuestionId(context: Context, questionId: String): Intent {
            return Intent(
                context,
                QuestionDetailActivity::class.java
            ).putExtra(ARG_QUESTION_ID, questionId)
        }
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var questionTitle: TextView
    private lateinit var questionAuthor: TextView
    private lateinit var questionBody: TextView

    private lateinit var retrofit: Retrofit
    private lateinit var stackoverflowApi: StackoverflowApi

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retrofit = Retrofit.Builder()
            .baseUrl("http://api.stackexchange.com/2.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

        questionId = intent?.getStringExtra("ARG_QUESTION_ID").toString()

        setContentView(R.layout.activity_question_detail)

        progressBar = findViewById(R.id.progress_bar_question_details)
        questionTitle = findViewById(R.id.text_view_question_title)
        questionAuthor = findViewById(R.id.text_view_question_author)
        questionBody = findViewById(R.id.text_view_question_body)

        supportActionBar?.apply {
            title = "Question details"
            setDisplayShowHomeEnabled(true)
        }
    }

    private suspend fun getQuestionDetails() {
        progressBar.visibility = View.VISIBLE
        questionTitle.visibility = View.GONE
        questionAuthor.visibility = View.GONE
        questionBody.visibility = View.GONE

        withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.getQuestionDetails(questionId)
                val question = response.body()?.questions?.get(0)
                withContext(Dispatchers.Main.immediate) {
                    questionTitle.text = question?.title
                    questionAuthor.text = "by ${question?.owner?.displayName}"
                    questionBody.text = Html.fromHtml(question?.body, Html.FROM_HTML_MODE_LEGACY)
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main.immediate) {
                    Toast.makeText(
                        this@QuestionDetailActivity,
                        "Error while trying to fetch the question details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } finally {
                withContext(Dispatchers.Main.immediate) {
                    progressBar.visibility = View.GONE
                    questionTitle.visibility = View.VISIBLE
                    questionAuthor.visibility = View.VISIBLE
                    questionBody.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        coroutineScope.launch {
            getQuestionDetails()
        }
    }
}