package com.zows.hubxrecruitmentcase.presentation.paywall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zows.hubxrecruitmentcase.common.setStartMargin
import com.zows.hubxrecruitmentcase.databinding.LayoutCardItemBinding

class CardAdapter(
    private val cardItems: List<CardItem>,
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(
        private val binding: LayoutCardItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardItem, position: Int) {
            with(binding) {
                if (position == 0) root.setStartMargin(24)

                tvTitle.text = item.title
                tvSubtitle.text = item.subtitle
                ivIcon.setImageResource(item.imageRes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding =
            LayoutCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(cardItems[position], position)

    override fun getItemCount(): Int = cardItems.size
}
