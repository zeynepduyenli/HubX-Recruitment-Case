package com.zows.hubxrecruitmentcase.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zows.hubxrecruitmentcase.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel
@Inject constructor(private val repository: OnboardingRepository) : ViewModel() {
    val isOnboardingCompleted = liveData {
        emit(repository.isOnboardingCompleted())
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            repository.completeOnboarding()
        }
    }
}
