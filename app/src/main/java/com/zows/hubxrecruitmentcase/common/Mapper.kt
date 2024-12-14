package com.zows.hubxrecruitmentcase.common

import com.zows.hubxrecruitmentcase.data.model.Image
import com.zows.hubxrecruitmentcase.data.model.Plant
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.data.model.QuestionResponse
import com.zows.hubxrecruitmentcase.domain.model.ImageDomain
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain


fun Image.toDomain(): ImageDomain {
    return ImageDomain(
        id = id,
        url = this.url
    )
}


fun Plant.toDomain(): List<PlantDomain> {
    return listOf(
        PlantDomain(
            id = id,
            title = title,
            imageDomain = image.toDomain()
        )
    )
}

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
