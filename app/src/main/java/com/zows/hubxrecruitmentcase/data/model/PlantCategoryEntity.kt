package com.zows.hubxrecruitmentcase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_category_table")
data class PlantCategoryEntity(
    @PrimaryKey
    val id: Int? = 0,
    val title: String,
    val url: String
)
