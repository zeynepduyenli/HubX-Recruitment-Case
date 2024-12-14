package com.zows.hubxrecruitmentcase.data.retrofit

import com.zows.hubxrecruitmentcase.common.Constants.GET_CATEGORIES
import com.zows.hubxrecruitmentcase.common.Constants.GET_QUESTIONS
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryResponse
import com.zows.hubxrecruitmentcase.data.model.QuestionResponse
import retrofit2.http.GET

interface PlantAPIService {

    @GET(GET_QUESTIONS)
    suspend fun allQuestions(): List<QuestionResponse>

    @GET(GET_CATEGORIES)
    suspend fun allCategories(): PlantCategoryResponse

}