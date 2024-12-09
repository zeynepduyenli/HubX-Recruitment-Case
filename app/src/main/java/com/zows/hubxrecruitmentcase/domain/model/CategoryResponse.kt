package com.zows.hubxrecruitmentcase.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val data: List<Plant>,
    val meta: Meta
)

@Serializable
data class Image(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: String?,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val provider_metadata: String?,
    val createdAt: String,
    val updatedAt: String
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

