package com.zows.hubxrecruitmentcase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zows.hubxrecruitmentcase.common.setStartMargin
import com.zows.hubxrecruitmentcase.databinding.LayoutQuestionItemBinding
import com.zows.hubxrecruitmentcase.domain.model.Question


class QuestionsAdapter(
    private var questions: List<Question>,
) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {
    class QuestionViewHolder(
        private val binding: LayoutQuestionItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question, position: Int) {
            with(binding) {
                if (position == 0) root.setStartMargin(24)
                tvQuestion.text = question.title

                Glide.with(ivQuestion.context)
                    .load(question.imageUri)
                    .into(ivQuestion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            LayoutQuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(questions[position], position)


    override fun getItemCount(): Int = questions.size

    fun updateList(newQuestions: List<Question>) {
        questions = newQuestions
        notifyDataSetChanged()
    }

}