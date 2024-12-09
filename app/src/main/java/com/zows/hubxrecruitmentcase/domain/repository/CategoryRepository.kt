package com.zows.hubxrecruitmentcase.domain.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.CategoryResponse
import com.zows.hubxrecruitmentcase.domain.model.Plant

interface CategoryRepository {
    suspend fun categories(): Resource<List<Plant>>
}