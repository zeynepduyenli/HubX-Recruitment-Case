package com.zows.hubxrecruitmentcase.domain.model

data class PlantDomain(
    val id: Int? = 0,
    val title: String? = "",
    val imageDomain: ImageDomain
)

data class ImageDomain(
    val id: Int,
    val url: String,
)
