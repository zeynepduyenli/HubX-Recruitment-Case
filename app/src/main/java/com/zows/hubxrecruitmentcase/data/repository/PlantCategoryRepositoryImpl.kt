package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.common.toEntity
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.PlantCategoryDao
import com.zows.hubxrecruitmentcase.domain.repository.PlantCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantCategoryRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val plantCategoryDao: PlantCategoryDao
) : PlantCategoryRepository {

    private var allPlantCategories: List<PlantCategoryEntity> = emptyList()

    override suspend fun fetchAndInsertAll(): Resource<List<PlantCategoryEntity>> =
        withContext(Dispatchers.IO) {
            val localDbData = plantCategoryDao.getAllPlantCategories()
            if (getAllPlantCategories().isNotEmpty()) {
                return@withContext Resource.Success(localDbData)
            } else {
                try {
                    val remoteResponse = plantAPIService.allCategories()
                    val result = remoteResponse.toEntity()
                    plantCategoryDao.insertAll(result)
                    Resource.Success(result)
                } catch (e: Exception) {
                    Resource.Error(e)
                }
            }
        }

    override suspend fun getAllPlantCategories(): List<PlantCategoryEntity> =
        withContext(Dispatchers.IO) {
            if (allPlantCategories.isNotEmpty()) {
                return@withContext allPlantCategories
            } else {
                allPlantCategories = plantCategoryDao.getAllPlantCategories()
                return@withContext allPlantCategories
            }
        }
}

