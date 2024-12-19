package com.zows.hubxrecruitmentcase.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    val id: Int,
    val title: String,
    val subtitle: String,
    @SerialName("image_uri") val imageUri: String,
    val uri: String,
    val order: Int
)

fun QuestionResponse.toEntity(): List<QuestionEntity> {
    return listOf(
        QuestionEntity(
            id = id,
            title = title,
            subtitle = subtitle,
            imageUri = imageUri,
            uri = uri,
            order = order
        )
    )
}

