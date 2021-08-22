package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mathsemilio.stackoverflowquestions.R
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question

class QuestionsListAdapter(
    private val questions: List<Question>,
    private val listener: OnQuestionClickListener
) : RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>() {

    interface OnQuestionClickListener {
        fun onQuestionClicked(questionId: String)
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val textViewQuestionTitle = itemView.findViewById<TextView>(R.id.text_view_question_title)

        private lateinit var question: Question

        init {
            itemView.setOnClickListener(this)
        }

        fun bindQuestion(question: Question) {
            this.question = question
            textViewQuestionTitle.text = question.title
        }

        override fun onClick(v: View?) {
            listener.onQuestionClicked(question.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.question_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindQuestion(questions[position])
    }

    override fun getItemCount() = questions.size
}