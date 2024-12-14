package com.zows.hubxrecruitmentcase.domain.use_case

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain
import com.zows.hubxrecruitmentcase.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlantCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    fun executeGetCategories(): Flow<Resource<List<PlantDomain>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.categories()
            emit(response)
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}



