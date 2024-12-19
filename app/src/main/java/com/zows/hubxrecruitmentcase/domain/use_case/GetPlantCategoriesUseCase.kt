package com.zows.hubxrecruitmentcase.domain.use_case

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.domain.repository.PlantCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlantCategoriesUseCase @Inject constructor(private val plantCategoryRepository: PlantCategoryRepository) {

    fun executeGetPlantCategories(): Flow<Resource<List<PlantCategoryEntity>>> = flow {
        emit(plantCategoryRepository.fetchAndInsertAll())
    }

    fun executeSearchPlantCategories(searchedTitle: String): Flow<List<PlantCategoryEntity>> {
        return plantCategoryRepository.searchPlantCategories(searchedTitle)
    }
}