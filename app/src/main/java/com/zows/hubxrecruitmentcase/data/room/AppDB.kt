package com.zows.hubxrecruitmentcase.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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


    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDB::class.java,
                    name = "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}