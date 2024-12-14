package com.zows.hubxrecruitmentcase.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zows.hubxrecruitmentcase.data.model.CategoryEntity
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.presentation.onboarding.OnboardingStatus

@Database(
    entities = [QuestionEntity::class, CategoryEntity::class, OnboardingStatus::class],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun onboardingStatusDao(): OnboardingStatusDao
}