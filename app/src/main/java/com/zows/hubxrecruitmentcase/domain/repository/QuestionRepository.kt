package com.zows.hubxrecruitmentcase.domain.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.QuestionDomain

interface QuestionRepository {
    suspend fun questions(): Resource<List<QuestionDomain>>
}