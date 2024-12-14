package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.QuestionResponse
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import com.zows.hubxrecruitmentcase.domain.model.QuestionDomain
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val questionDao: QuestionDao
) : QuestionRepository {
    override suspend fun questions(): Resource<List<QuestionDomain>> {
        return try {
            val remoteResponse = plantAPIService.allQuestions()
            run {
                val result = remoteResponse.flatMap { it.toDomain() }
                Resource.Success(result)
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    private fun QuestionResponse.toDomain(): List<QuestionDomain> {
        return listOf(
            QuestionDomain(
                id = id,
                title = title,
                subtitle = subtitle,
                imageUri = imageUri,
                uri = uri,
                order = order
            )
        )
    }


}