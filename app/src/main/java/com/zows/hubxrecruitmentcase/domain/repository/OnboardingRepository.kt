package com.zows.hubxrecruitmentcase.domain.repository

interface OnboardingRepository {
    suspend fun isOnboardingCompleted(): Boolean
    suspend fun completeOnboarding()
}