package com.zows.hubxrecruitmentcase.presentation.home.categories

import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity

data class CategoriesState(
    val isLoading: Boolean = false,
    val plantCategoriesList: List<PlantCategoryEntity>? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)
