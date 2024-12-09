package com.zows.hubxrecruitmentcase.domain.model

data class Plant(
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
    val publishedAt: String,
    val title: String,
    val rank: Int,
    val image: Image
)
