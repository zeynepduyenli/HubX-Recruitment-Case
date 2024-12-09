package com.zows.hubxrecruitmentcase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zows.hubxrecruitmentcase.presentation.onboarding.OnboardingStatus

@Dao
interface OnboardingStatusDao {
    @Query("SELECT isOnboardCompleted FROM onboarding_status WHERE id = 0")
    suspend fun getOnboardingStatus(): Boolean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setOnboardingStatus(onboardingStatus: OnboardingStatus)
}
