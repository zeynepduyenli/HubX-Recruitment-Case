package com.zows.hubxrecruitmentcase.di

import com.zows.hubxrecruitmentcase.data.repository.OnboardingRepositoryImpl
import com.zows.hubxrecruitmentcase.data.repository.PlantCategoryRepositoryImpl
import com.zows.hubxrecruitmentcase.data.repository.QuestionRepositoryImpl
import com.zows.hubxrecruitmentcase.data.retrofit.PlantAPIService
import com.zows.hubxrecruitmentcase.data.room.OnboardingStatusDao
import com.zows.hubxrecruitmentcase.data.room.PlantCategoryDao
import com.zows.hubxrecruitmentcase.data.room.QuestionDao
import com.zows.hubxrecruitmentcase.domain.repository.OnboardingRepository
import com.zows.hubxrecruitmentcase.domain.repository.PlantCategoryRepository
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
        plantAPIService: PlantAPIService,
        questionDao: QuestionDao
    ): QuestionRepository {
        return QuestionRepositoryImpl(plantAPIService, questionDao)
    }

    @Provides
    @Singleton
    fun providePlantCategoryRepository(
        plantAPIService: PlantAPIService,
        plantCategoryDao: PlantCategoryDao
    ): PlantCategoryRepository {
        return PlantCategoryRepositoryImpl(plantAPIService, plantCategoryDao)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        onboardingStatusDao: OnboardingStatusDao
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(onboardingStatusDao)
    }
}