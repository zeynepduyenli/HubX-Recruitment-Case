package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.common.toEntity
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionRepositoryImpl(
    private val plantAPIService: PlantAPIService,
    private val questionDao: QuestionDao
) : QuestionRepository {

    private var allQuestions: List<QuestionEntity> = emptyList()

    override suspend fun fetchAndInsertAll(): Resource<List<QuestionEntity>> =
        withContext(Dispatchers.IO) {
            val localDbData = questionDao.getAllQuestions()
            if (getAllQuestions().isNotEmpty()) {
                return@withContext Resource.Success(localDbData)
            } else {
                try {
                    val remoteResponse = plantAPIService.allQuestions()
                    val result = remoteResponse.flatMap { it.toEntity() }
                    questionDao.insertAll(result)
                    Resource.Success(result)
                } catch (e: Exception) {
                    Resource.Error(e)
                }
            }
        }

    override suspend fun getAllQuestions(): List<QuestionEntity> = withContext(Dispatchers.IO) {
        if (allQuestions.isNotEmpty()) {
            return@withContext allQuestions
        } else {
            allQuestions = questionDao.getAllQuestions()
            return@withContext allQuestions
        }
    }
}