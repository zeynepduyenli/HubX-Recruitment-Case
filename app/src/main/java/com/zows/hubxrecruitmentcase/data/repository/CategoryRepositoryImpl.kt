package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.retrofit.DataService
import com.zows.hubxrecruitmentcase.data.room.CategoryDao
import com.zows.hubxrecruitmentcase.domain.model.Plant
import com.zows.hubxrecruitmentcase.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val dataService: DataService,
    private val categoryDao: CategoryDao
):CategoryRepository {
    override suspend fun categories(): Resource<List<Plant>> {
        return try {
            val response = dataService.allCategories()

            if (response.isSuccessful) {
                Resource.Success(response.body()?.data.orEmpty())
            } else {
                Resource.Fail(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}