package com.zows.hubxrecruitmentcase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zows.hubxrecruitmentcase.common.setStartMargin
import com.zows.hubxrecruitmentcase.databinding.LayoutQuestionItemBinding
import com.zows.hubxrecruitmentcase.domain.model.Question


class QuestionsAdapter :
    ListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

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
        holder.bind(getItem(position), position)


    class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }
}