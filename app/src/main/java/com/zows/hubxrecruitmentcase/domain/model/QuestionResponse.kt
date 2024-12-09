package com.zows.hubxrecruitmentcase.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    val data: List<Question>
)

@Serializable
data class Question(
    val id: Int,
    val title: String,
    val subtitle: String,
    @SerialName("image_uri") val imageUri: String,
    val uri: String,
    val order: Int
)

