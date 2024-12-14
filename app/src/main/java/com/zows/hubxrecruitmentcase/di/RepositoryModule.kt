package com.zows.hubxrecruitmentcase.di

import com.zows.hubxrecruitmentcase.data.repository.CategoryRepositoryImpl
import com.zows.hubxrecruitmentcase.data.repository.OnboardingRepositoryImpl
import com.zows.hubxrecruitmentcase.data.repository.QuestionRepositoryImpl
import com.zows.hubxrecruitmentcase.data.retrofit.DataService
import com.zows.hubxrecruitmentcase.data.room.CategoryDao
import com.zows.hubxrecruitmentcase.data.room.OnboardingStatusDao
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import com.zows.hubxrecruitmentcase.domain.repository.CategoryRepository
import com.zows.hubxrecruitmentcase.domain.repository.OnboardingRepository
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionRepository(
        dataService: DataService,
        questionDao: QuestionDao
    ): QuestionRepository {
        return QuestionRepositoryImpl(dataService, questionDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        dataService: DataService,
        categoryDao: CategoryDao
    ): CategoryRepository {
        return CategoryRepositoryImpl(dataService, categoryDao)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        onboardingStatusDao: OnboardingStatusDao
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(onboardingStatusDao)
    }
}