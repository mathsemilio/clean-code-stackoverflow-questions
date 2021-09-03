package com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mathsemilio.stackoverflowquestions.domain.model.question.Question
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.listitem.QuestionListItemView
import com.github.mathsemilio.stackoverflowquestions.ui.screens.questionslist.view.listitem.QuestionListItemViewImpl

class QuestionsListAdapter(
    private val questions: List<Question>,
    private val listener: OnQuestionClickListener
) : RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>(),
    QuestionListItemView.Listener {

    interface OnQuestionClickListener {
        fun onQuestionClicked(questionId: String)
    }

    inner class ViewHolder(
        private val listItemView: QuestionListItemView
    ) : RecyclerView.ViewHolder(listItemView.rootView) {

        fun bind(question: Question) = listItemView.bindQuestion(question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuestionListItemViewImpl(
                LayoutInflater.from(parent.context),
                parent
            ).also { view ->
                view.addListener(this)
            }
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount() = questions.size

    override fun onQuestionClicked(questionId: String) {
        listener.onQuestionClicked(questionId)
    }
}