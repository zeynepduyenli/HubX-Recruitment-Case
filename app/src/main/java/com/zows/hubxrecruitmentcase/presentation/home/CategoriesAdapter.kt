package com.zows.hubxrecruitmentcase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zows.hubxrecruitmentcase.databinding.LayoutCategoryItemBinding
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain

class CategoriesAdapter :
    ListAdapter<PlantDomain, CategoriesAdapter.CategoryViewHolder>(PlantDiffCallback()) {

    class CategoryViewHolder(
        private val binding: LayoutCategoryItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: PlantDomain) {
            with(binding) {
                tvCategory.text = plant.title
                Glide.with(ivCategory.context)
                    .load(plant.imageDomain.url)
                    .into(ivCategory)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            LayoutCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlantDiffCallback : DiffUtil.ItemCallback<PlantDomain>() {
        override fun areItemsTheSame(oldItem: PlantDomain, newItem: PlantDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantDomain, newItem: PlantDomain): Boolean {
            return oldItem == newItem
        }
    }
}