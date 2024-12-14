package com.zows.hubxrecruitmentcase.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.PlantDomain
import com.zows.hubxrecruitmentcase.domain.model.Question
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository
import com.zows.hubxrecruitmentcase.domain.use_case.GetPlantCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val getPlantCategoriesUseCase: GetPlantCategoriesUseCase,
) : ViewModel() {

    private var _questionsState = MutableLiveData(QuestionsState(isLoading = true))
    val questionsState: LiveData<QuestionsState>
        get() = _questionsState

    private var _categoriesState = MutableLiveData(CategoriesState(isLoading = true))
    val categoriesState: LiveData<CategoriesState>
        get() = _categoriesState

    fun getQuestions() {
        viewModelScope.launch {
            when (val response = questionRepository.questions()) {
                is Resource.Success -> {
                    _questionsState.value = QuestionsState(
                        isLoading = false,
                        questionList = response.data,
                    )
                    Log.d("API Test:", "Questions: ${response.data}")
                }

                is Resource.Fail -> {
                    _questionsState.value =
                        QuestionsState(isLoading = false, failMessage = response.message)
                }

                is Resource.Error -> {
                    _questionsState.value =
                        QuestionsState(isLoading = false, errorMessage = response.throwable.message)
                }

                is Resource.Loading -> TODO()
            }
        }
    }

    private fun getPlantCategories() {
        viewModelScope.launch {
            getPlantCategoriesUseCase.executeGetCategories()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state
                            _categoriesState.value = CategoriesState(isLoading = true)
                        }

                        is Resource.Success -> {
                            // Handle success, no need to map again
                            _categoriesState.value =
                                CategoriesState(categoriesList = resource.data, isLoading = false)
                        }

                        is Resource.Error -> {
                            // Handle error
                            _categoriesState.value = CategoriesState(
                                isLoading = false,
                                errorMessage = resource.throwable.message
                            )
                        }

                        is Resource.Fail -> TODO()
                    }
                }
                .launchIn(viewModelScope)  // Collect the flow in the ViewModel scope
        }
    }


    fun loadPlantCategories() {
        getPlantCategories()
    }
}

data class QuestionsState(
    val isLoading: Boolean = false,
    val questionList: List<Question>? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)

data class CategoriesState(
    val isLoading: Boolean = false,
    val categoriesList: List<PlantDomain>? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)