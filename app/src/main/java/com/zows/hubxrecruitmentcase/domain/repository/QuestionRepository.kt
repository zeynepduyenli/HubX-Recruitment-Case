package com.zows.hubxrecruitmentcase.domain.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.Question

interface QuestionRepository {
    suspend fun questions(): Resource<List<Question>>
}