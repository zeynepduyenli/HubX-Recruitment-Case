package com.zows.hubxrecruitmentcase.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.domain.use_case.GetPlantCategoriesUseCase
import com.zows.hubxrecruitmentcase.domain.use_case.GetQuestionsUseCase
import com.zows.hubxrecruitmentcase.presentation.home.categories.CategoriesState
import com.zows.hubxrecruitmentcase.presentation.home.questions.QuestionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val getPlantCategoriesUseCase: GetPlantCategoriesUseCase,
) : ViewModel() {

    private var _questionsState = MutableStateFlow(QuestionsState())
    val questionsState: StateFlow<QuestionsState> = _questionsState

    private var _categoriesState = MutableStateFlow(CategoriesState())
    val categoriesState: StateFlow<CategoriesState> = _categoriesState

    init {
        getQuestions()
        getPlantCategories()
    }

    private fun getQuestions() {
        getQuestionsUseCase.executeGetQuestions()
            .onStart { _questionsState.value = _questionsState.value.copy(isLoading = true) }
            .onEach { resource ->
                when (resource) {
                    is Resource.Success ->
                        _questionsState.value =
                            _questionsState.value.copy(questionList = resource.data)

                    is Resource.Error -> _questionsState.value = _questionsState.value.copy(
                        isLoading = false,
                        errorMessage = resource.throwable.message
                    )

                    is Resource.Fail -> _questionsState.value =
                        _questionsState.value.copy(failMessage = resource.message)
                }
            }
            .onCompletion { _questionsState.value = _questionsState.value.copy(isLoading = false) }
            .launchIn(viewModelScope)
    }

    private fun getPlantCategories() {
        getPlantCategoriesUseCase.executeGetPlantCategories()
            .onStart { _categoriesState.value = CategoriesState(isLoading = true) }
            .onEach { resource ->
                when (resource) {
                    is Resource.Success ->
                        _categoriesState.value =
                            _categoriesState.value.copy(plantCategoriesList = resource.data)

                    is Resource.Error ->
                        _categoriesState.value = _categoriesState.value.copy(
                            isLoading = false,
                            errorMessage = resource.throwable.message
                        )

                    is Resource.Fail -> _categoriesState.value = _categoriesState.value.copy(failMessage = resource.message)
                }
            }
            .onCompletion { _categoriesState.value = _categoriesState.value.copy(isLoading = false) }
            .launchIn(viewModelScope)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<PlantCategoryEntity>> =
        getPlantCategoriesUseCase.executeSearchPlantCategories(searchQuery).asLiveData()
}