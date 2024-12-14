package com.zows.hubxrecruitmentcase.domain.model

data class QuestionDomain(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val uri: String,
    val order: Int
)

