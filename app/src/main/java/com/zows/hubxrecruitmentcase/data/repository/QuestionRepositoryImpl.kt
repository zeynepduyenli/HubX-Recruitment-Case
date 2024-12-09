package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.retrofit.DataService
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import com.zows.hubxrecruitmentcase.domain.model.Question
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val dataService: DataService,
    private val questionDao: QuestionDao
) : QuestionRepository {
    override suspend fun questions(): Resource<List<Question>> {
        return try {
            val response = dataService.allQuestions()

            if (response.isSuccessful) {
                Resource.Success(response.body().orEmpty())
            } else {
                Resource.Fail(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }


}