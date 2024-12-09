package com.zows.hubxrecruitmentcase.di

import android.content.Context
import androidx.room.Room
import com.zows.hubxrecruitmentcase.data.room.AppDB
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDBModule {
    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(
            context,
            AppDB::class.java,
            "database"
        ).build()
    }

    @Provides
    fun provideWordDao(appDB: AppDB): QuestionDao {
        return appDB.questionDao()
    }
}