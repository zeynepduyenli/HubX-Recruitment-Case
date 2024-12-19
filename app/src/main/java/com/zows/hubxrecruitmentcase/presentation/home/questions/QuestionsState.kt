package com.zows.hubxrecruitmentcase.presentation.home.questions

import com.zows.hubxrecruitmentcase.data.model.QuestionEntity

data class QuestionsState(
    val isLoading: Boolean = false,
    val questionList: List<QuestionEntity> = emptyList(),
    val errorMessage: String? = null,
    val failMessage: String? = null
)
