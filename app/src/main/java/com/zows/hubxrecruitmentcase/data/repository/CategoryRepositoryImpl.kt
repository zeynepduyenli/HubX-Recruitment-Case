package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.common.toDomain
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.CategoryDao
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain
import com.zows.hubxrecruitmentcase.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val categoryDao: CategoryDao
) : CategoryRepository {

//    suspend fun categoryDaoData(): Flow<List<PlantDomain>> {
//        return categoryDao.getPlantCategories()
//    }

    override suspend fun categories(): Resource<List<PlantDomain>> {
        return try {
            val remoteResponse = plantAPIService.allCategories()
            val data = remoteResponse.data

            run {
                val plantDomains = data.flatMap { it.toDomain() }
                Resource.Success(plantDomains)
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

//    suspend fun localCategories(): Resource<List<Plant>> {
//
//    }
//
//    suspend fun addCategories(plant: PlantDomain) {
//        val categoryResponse = listOf(
//            plant.toDataModel()
//        )
//
//        categoryDao.addCategories(categoryResponse)
//    }


//    private fun PlantDomain.toDataModel(): Plant {
//        return Plant(
//            id = this.id,
//            title = this.title,
//            image = this.imageDomain.toDataModel()  // Assuming you have a mapping for image
//        )
//    }
//
//    private fun ImageDomain.toDataModel(): Image {
//        return Image(
//            id = this.id,
//            url = this.url
//        )
//    }
}

