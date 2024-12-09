package com.zows.hubxrecruitmentcase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zows.hubxrecruitmentcase.common.setStartMargin
import com.zows.hubxrecruitmentcase.databinding.LayoutCategoryItemBinding
import com.zows.hubxrecruitmentcase.databinding.LayoutQuestionItemBinding
import com.zows.hubxrecruitmentcase.domain.model.Plant
import com.zows.hubxrecruitmentcase.domain.model.Question


class CategoriesAdapter(
    private var plants: List<Plant>,
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(
        private val binding: LayoutCategoryItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant, position: Int) {
            with(binding) {
                tvCategory.text = plant.title
                Glide.with(ivCategory.context)
                    .load(plant.image.url)
                    .into(ivCategory)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            LayoutCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(plants[position], position)


    override fun getItemCount(): Int = plants.size

    fun updateList(newPlants: List<Plant>) {
        plants = newPlants
        notifyDataSetChanged()
    }

}