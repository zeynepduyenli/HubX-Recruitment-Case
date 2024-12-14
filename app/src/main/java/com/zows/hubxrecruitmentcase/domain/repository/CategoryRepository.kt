package com.zows.hubxrecruitmentcase.domain.repository

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain

interface CategoryRepository {
    suspend fun categories(): Resource<List<PlantDomain>>
}