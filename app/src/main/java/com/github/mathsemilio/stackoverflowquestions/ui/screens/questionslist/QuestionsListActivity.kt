package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.networking.StackoverflowApi
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questiondetails.QuestionDetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsListActivity : AppCompatActivity(), QuestionsListAdapter.OnQuestionClickListener {

    private lateinit var progressBarQuestionsList: ProgressBar
    private lateinit var recyclerViewQuestionsList: RecyclerView

    private lateinit var retrofit: Retrofit
    private lateinit var stackoverflowApi: StackoverflowApi

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retrofit = Retrofit.Builder()
            .baseUrl("http://api.stackexchange.com/2.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

        setContentView(R.layout.activity_questions_list)

        progressBarQuestionsList = findViewById(R.id.progress_bar_questions_list)
        recyclerViewQuestionsList = findViewById(R.id.recycler_view_questions_list)

        supportActionBar?.title = "Last active questions"
    }

    private suspend fun getLastActiveQuestions() {
        progressBarQuestionsList.visibility = View.VISIBLE
        recyclerViewQuestionsList.visibility = View.GONE

        withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.getLastActiveQuestions()
                val questions = response.body()?.questions
                withContext(Dispatchers.Main.immediate) {
                    val adapter = QuestionsListAdapter(questions!!, this@QuestionsListActivity)
                    recyclerViewQuestionsList.adapter = adapter
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main.immediate) {
                    Toast.makeText(
                        this@QuestionsListActivity,
                        "Error while trying to fetch questions",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } finally {
                withContext(Dispatchers.Main.immediate) {
                    progressBarQuestionsList.visibility = View.GONE
                    recyclerViewQuestionsList.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onQuestionClicked(questionId: String) {
        val intent = Intent(this, QuestionDetailActivity::class.java)
        intent.putExtra("ARG_QUESTION_ID", questionId)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        coroutineScope.launch {
            getLastActiveQuestions()
        }
    }
}