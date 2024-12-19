package com.zows.hubxrecruitmentcase.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PlantCategoryResponse(
    val data: List<Plant>,
    val meta: Meta? = null
)

@Serializable
data class Plant(
    val id: Int? = 0,
    val title: String,
    val image: Image
)

@Serializable
data class Image(
    val id: Int,
    val url: String
)

@Serializable
data class Meta(
    val pagination: Pagination
)

@Serializable
data class Pagination(
    val page: Int,
    val pageSize: Int,
    val pageCount: Int,
    val total: Int
)

fun PlantCategoryResponse.toEntity(): List<PlantCategoryEntity> {
    return data.map { plant ->
        PlantCategoryEntity(
            id = plant.id,
            title = plant.title,
            url = plant.image.url
        )
    }
}

