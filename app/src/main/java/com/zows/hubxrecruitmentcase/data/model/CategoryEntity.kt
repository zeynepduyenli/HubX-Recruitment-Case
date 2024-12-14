package com.zows.hubxrecruitmentcase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    val id: Int? = 0,
    val title: String,
    val url: String
)
