package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.data.model.toEntity
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val questionDao: QuestionDao
) : QuestionRepository {

    private var allQuestions: List<QuestionEntity> = emptyList()

    override suspend fun fetchAndInsertAll(): Resource<List<QuestionEntity>> {
        val localDbData = questionDao.getAllQuestions()
        if (getAllQuestions().isNotEmpty()) {
            return Resource.Success(localDbData)
        } else {
            try {
                val remoteResponse = plantAPIService.allQuestions()
                val result = remoteResponse.flatMap { it.toEntity() }
                questionDao.insertAll(result)
                return Resource.Success(result)
            } catch (e: Exception) {
                return Resource.Error(e)
            }
        }
    }

    override suspend fun getAllQuestions(): List<QuestionEntity> {
        if (allQuestions.isNotEmpty()) {
            return allQuestions
        } else {
            allQuestions = questionDao.getAllQuestions()
            return allQuestions
        }
    }
}