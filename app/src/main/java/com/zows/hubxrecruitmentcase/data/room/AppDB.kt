package com.zows.hubxrecruitmentcase.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity

@Database(entities = [QuestionEntity::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}