package com.zows.hubxrecruitmentcase.presentation.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zows.hubxrecruitmentcase.common.setStartMargin
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.databinding.LayoutQuestionItemBinding


class QuestionsAdapter :
    ListAdapter<QuestionEntity, QuestionsAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    class QuestionViewHolder(
        private val binding: LayoutQuestionItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(questionEntity: QuestionEntity, position: Int) {
            with(binding) {
                if (position == 0) root.setStartMargin(24)
                tvQuestion.text = questionEntity.title
                Glide.with(ivQuestion.context)
                    .load(questionEntity.imageUri)
                    .into(ivQuestion)

                root.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(questionEntity.uri))
                    itemView.context.startActivity(intent)
                }
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


    class QuestionDiffCallback : DiffUtil.ItemCallback<QuestionEntity>() {
        override fun areItemsTheSame(oldItem: QuestionEntity, newItem: QuestionEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuestionEntity, newItem: QuestionEntity): Boolean {
            return oldItem == newItem
        }
    }
}