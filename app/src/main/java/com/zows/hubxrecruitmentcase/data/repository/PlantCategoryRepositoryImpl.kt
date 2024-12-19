package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.data.model.toEntity
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.PlantCategoryDao
import com.zows.hubxrecruitmentcase.domain.repository.PlantCategoryRepository
import kotlinx.coroutines.flow.Flow

class PlantCategoryRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val plantCategoryDao: PlantCategoryDao
) : PlantCategoryRepository {

    private var allPlantCategories: List<PlantCategoryEntity> = emptyList()

    override suspend fun fetchAndInsertAll(): Resource<List<PlantCategoryEntity>> {
        val localDbData = plantCategoryDao.getAllPlantCategories()
        if (getAllPlantCategories().isNotEmpty()) {
            return Resource.Success(localDbData)
        } else {
            try {
                val remoteResponse = plantAPIService.allCategories()
                val result = remoteResponse.toEntity()
                plantCategoryDao.insertAll(result)
                return Resource.Success(result)
            } catch (e: Exception) {
                return Resource.Error(e)
            }
        }
    }

    override suspend fun getAllPlantCategories(): List<PlantCategoryEntity> {
            if (allPlantCategories.isNotEmpty()) {
                return allPlantCategories
            } else {
                allPlantCategories = plantCategoryDao.getAllPlantCategories()
                return allPlantCategories
            }
        }

    override fun searchPlantCategories(searchQuery: String): Flow<List<PlantCategoryEntity>> {
        return plantCategoryDao.getSearchedPlantCategories(searchQuery)
    }
}

