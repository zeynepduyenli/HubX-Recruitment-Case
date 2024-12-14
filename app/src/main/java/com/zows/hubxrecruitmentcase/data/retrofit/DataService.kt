package com.zows.hubxrecruitmentcase.data.retrofit

import com.zows.hubxrecruitmentcase.common.Constants.GET_CATEGORIES
import com.zows.hubxrecruitmentcase.common.Constants.GET_QUESTIONS
import com.zows.hubxrecruitmentcase.data.model.CategoryResponse
import com.zows.hubxrecruitmentcase.domain.model.Question
import retrofit2.Response
import retrofit2.http.GET

interface DataService {

    @GET(GET_QUESTIONS)
    suspend fun allQuestions(): Response<List<Question>>

    @GET(GET_CATEGORIES)
    suspend fun allCategories(): CategoryResponse

}