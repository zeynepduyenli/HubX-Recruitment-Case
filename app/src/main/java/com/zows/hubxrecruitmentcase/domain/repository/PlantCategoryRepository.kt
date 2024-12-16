package com.zows.hubxrecruitmentcase.domain.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import kotlinx.coroutines.flow.Flow

interface PlantCategoryRepository {
    suspend fun fetchAndInsertAll(): Resource<List<PlantCategoryEntity>>
    suspend fun getAllPlantCategories(): List<PlantCategoryEntity>
    fun searchPlantCategories(searchQuery: String): Flow<List<PlantCategoryEntity>>
}