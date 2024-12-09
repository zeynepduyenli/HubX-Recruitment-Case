package com.zows.hubxrecruitmentcase.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.domain.model.Plant
import com.zows.hubxrecruitmentcase.domain.model.Question
import com.zows.hubxrecruitmentcase.domain.repository.CategoryRepository
import com.zows.hubxrecruitmentcase.domain.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val categoryRepository: CategoryRepository,
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
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            when (val response = categoryRepository.categories()) {
                is Resource.Success -> {
                    _categoriesState.value = CategoriesState(
                        isLoading = false,
                        categoriesList = response.data,
                    )
                    Log.d("API Test:", "Categories: ${response.data}")
                }

                is Resource.Fail -> {
                    _categoriesState.value =
                        CategoriesState(isLoading = false, failMessage = response.message)
                }

                is Resource.Error -> {
                    _categoriesState.value =
                        CategoriesState(isLoading = false, errorMessage = response.throwable.message)
                }
            }
        }
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
    val categoriesList: List<Plant>? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)