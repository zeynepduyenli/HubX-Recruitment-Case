package com.zows.hubxrecruitmentcase.domain.use_case

import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.QuestionDomain
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(private val repository: QuestionRepository) {
    fun executeGetQuestions(): Flow<Resource<List<QuestionDomain>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.questions()
            emit(response)
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}