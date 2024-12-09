package com.zows.hubxrecruitmentcase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class QuestionEntity(
    @PrimaryKey
    val id: Int? = 0,
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val uri: String,
    val order: Int
)
