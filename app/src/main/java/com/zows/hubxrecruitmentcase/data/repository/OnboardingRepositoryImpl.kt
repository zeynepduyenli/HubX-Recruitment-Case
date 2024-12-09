package com.zows.hubxrecruitmentcase.data.repository

import com.zows.hubxrecruitmentcase.data.room.OnboardingStatusDao
import com.zows.hubxrecruitmentcase.domain.repository.OnboardingRepository
import com.zows.hubxrecruitmentcase.presentation.onboarding.OnboardingStatus

class OnboardingRepositoryImpl(private val onboardingStatusDao: OnboardingStatusDao) :
    OnboardingRepository {
    override suspend fun isOnboardingCompleted(): Boolean {
        return onboardingStatusDao.getOnboardingStatus() ?: false
    }

    override suspend fun completeOnboarding() {
        onboardingStatusDao.setOnboardingStatus(OnboardingStatus(isOnboardCompleted = true))
    }

}