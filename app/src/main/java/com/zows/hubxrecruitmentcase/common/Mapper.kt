package com.zows.hubxrecruitmentcase.common

import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryResponse
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.data.model.QuestionResponse


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

fun PlantCategoryResponse.toEntity(): List<PlantCategoryEntity> {
    return data.map { plant ->
        PlantCategoryEntity(
            id = plant.id,
            title = plant.title,
            url = plant.image.url
        )
    }
}

