package com.zows.hubxrecruitmentcase.presentation.home.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.databinding.LayoutCategoryItemBinding

class PlantCategoriesAdapter :
    ListAdapter<PlantCategoryEntity, PlantCategoriesAdapter.CategoryViewHolder>(PlantDiffCallback()) {

    class CategoryViewHolder(
        private val binding: LayoutCategoryItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: PlantCategoryEntity) {
            with(binding) {
                tvCategory.text = plant.title
                Glide.with(ivCategory.context)
                    .load(plant.url)
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

    class PlantDiffCallback : DiffUtil.ItemCallback<PlantCategoryEntity>() {
        override fun areItemsTheSame(
            oldItem: PlantCategoryEntity,
            newItem: PlantCategoryEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PlantCategoryEntity,
            newItem: PlantCategoryEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}