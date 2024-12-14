package com.zows.hubxrecruitmentcase.domain.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity

interface QuestionRepository {
    suspend fun fetchAndInsertAll(): Resource<List<QuestionEntity>>
    suspend fun getAllQuestions(): List<QuestionEntity>
}