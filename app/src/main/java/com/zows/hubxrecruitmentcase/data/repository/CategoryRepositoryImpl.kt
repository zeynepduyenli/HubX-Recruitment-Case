package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.Image
import com.zows.hubxrecruitmentcase.data.model.Plant
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.CategoryDao
import com.zows.hubxrecruitmentcase.domain.model.ImageDomain
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain
import com.zows.hubxrecruitmentcase.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override suspend fun categories(): Resource<List<PlantDomain>> {
        return try {
            val categoryResponse = plantAPIService.allCategories()  // Get raw data from the service
            val data = categoryResponse.data  // Assuming data is a list of plants

            run {
                val plantDomains = data.flatMap { plant -> plant.toDomain() }
                Resource.Success(plantDomains)
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    private fun Image.toDomain(): ImageDomain {
        return ImageDomain(
            id = id,
            url = this.url
        )
    }


    private fun Plant.toDomain(): List<PlantDomain> {
        return listOf(
            PlantDomain(
                id = id,
                title = title,
                imageDomain = image.toDomain()
            )
        )
    }
}

