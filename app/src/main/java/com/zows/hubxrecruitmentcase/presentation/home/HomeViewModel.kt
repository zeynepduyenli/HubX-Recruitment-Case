package com.zows.hubxrecruitmentcase.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zows.hubxrecruitmentcase.common.Resource
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity
import com.zows.hubxrecruitmentcase.data.model.QuestionEntity
import com.zows.hubxrecruitmentcase.domain.use_case.GetPlantCategoriesUseCase
import com.zows.hubxrecruitmentcase.domain.use_case.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val getPlantCategoriesUseCase: GetPlantCategoriesUseCase,
) : ViewModel() {

    private var _questionsState = MutableLiveData(QuestionsState(isLoading = true))
    val questionsState: LiveData<QuestionsState>
        get() = _questionsState

    private var _categoriesState = MutableLiveData(CategoriesState(isLoading = true))
    val categoriesState: LiveData<CategoriesState>
        get() = _categoriesState

    init {
        viewModelScope.launch {
            loadQuestions()
            loadPlantCategories()
        }
    }


    fun loadQuestions() {
        getQuestions()
    }

    fun loadPlantCategories() {
        getPlantCategories()
    }

    private fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuestionsUseCase.executeGetQuestions()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Loading -> _questionsState.value =
                            QuestionsState(isLoading = true)

                        is Resource.Success ->
                            _questionsState.value =
                                QuestionsState(
                                    questionList = resource.data,
                                    isLoading = false
                                )

                        is Resource.Error -> _questionsState.value = QuestionsState(
                            isLoading = false,
                            errorMessage = resource.throwable.message
                        )

                        is Resource.Fail -> QuestionsState(failMessage = resource.message)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun getPlantCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getPlantCategoriesUseCase.executeGetPlantCategories()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Loading ->
                            _categoriesState.value = CategoriesState(isLoading = true)

                        is Resource.Success ->
                            _categoriesState.value =
                                CategoriesState(
                                    plantCategoriesList = resource.data,
                                    isLoading = false
                                )

                        is Resource.Error ->
                            _categoriesState.value = CategoriesState(
                                isLoading = false,
                                errorMessage = resource.throwable.message
                            )

                        is Resource.Fail -> CategoriesState(failMessage = resource.message)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<PlantCategoryEntity>> {
        return getPlantCategoriesUseCase.executeSearchPlantCategories(searchQuery).asLiveData()
    }
}

data class QuestionsState(
    val isLoading: Boolean = false,
    val questionList: List<QuestionEntity> = emptyList(),
    val errorMessage: String? = null,
    val failMessage: String? = null
)

data class CategoriesState(
    val isLoading: Boolean = false,
    val plantCategoriesList: List<PlantCategoryEntity>? = null,
    var searchedTitle: String? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)

data class SearchState(
    var searchText: String? = null,
    val plantCategoriesList: List<PlantCategoryEntity>? = null,
)