package com.zows.hubxrecruitmentcase.presentation.home

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.hideKeyboard
import com.zows.hubxrecruitmentcase.common.setStatusBarTextColor
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentHomeBinding
import com.zows.hubxrecruitmentcase.presentation.home.categories.PlantCategoriesAdapter
import com.zows.hubxrecruitmentcase.presentation.home.questions.QuestionsAdapter
import com.zows.hubxrecruitmentcase.presentation.paywall.SpacingItemDecoration
import com.zows.hubxrecruitmentcase.presentation.paywall.TwoColumnItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val questionsAdapter by lazy { QuestionsAdapter() }
    private val plantCategoriesAdapter by lazy { PlantCategoriesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.setStatusBarTextColor(isLightText = false)
        setupRecyclerView()
        initPremiumTitleShader()
        onSearchViewObserved(view)
        initObserver()
    }

    private fun onSearchViewObserved(view: View) {
        with(binding) {
            premiumCard.setOnClickListener {
                findNavController().navigate(R.id.action_homeToPaywall)
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty()) {
                        searchDatabase(newText)
                        toggleVisibility(View.GONE)
                    } else {
                        toggleVisibility(View.VISIBLE)
                        hideKeyboard(requireActivity(), view)
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun toggleVisibility(visibility: Int) {
        with(binding){
            premiumCard.visibility = visibility
            tvQuestionsTitle.visibility = visibility
            recyclerViewQuestions.visibility = visibility
        }
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerViewQuestions.adapter = questionsAdapter
        val space = resources.getDimensionPixelSize(R.dimen.spacing_medium)
        recyclerViewQuestions.addItemDecoration(SpacingItemDecoration(space))

        val spaceGrid = resources.getDimensionPixelSize(R.dimen.spacing_small)
        recyclerViewCategories.adapter = plantCategoriesAdapter
        recyclerViewCategories.addItemDecoration(TwoColumnItemDecoration(2, spaceGrid))
    }

    private fun initPremiumTitleShader() = with(binding) {
        tvPremiumTitle.post {
            val shader = LinearGradient(
                0f, tvPremiumTitle.height.toFloat(), tvPremiumTitle.width.toFloat(), 0f,
                intArrayOf(Color.parseColor("#E5C990"), Color.parseColor("#E4B046")),
                null, Shader.TileMode.CLAMP
            )
            tvPremiumTitle.paint.shader = shader
            tvPremiumTitle.invalidate()
        }
        tvPremiumSubtitle.post {
            val shader = LinearGradient(
                0f, tvPremiumSubtitle.height.toFloat(), tvPremiumSubtitle.width.toFloat(), 0f,
                intArrayOf(Color.parseColor("#E4B046"), Color.parseColor("#E5C990")),
                null, Shader.TileMode.CLAMP
            )
            tvPremiumSubtitle.paint.shader = shader
            tvPremiumSubtitle.invalidate()
        }
    }

    private fun initObserver() = with(binding) {
        lifecycleScope.launch {
            combine(
                viewModel.questionsState,
                viewModel.categoriesState
            ) { questionsState, categoriesState ->
                questionsState.isLoading || categoriesState.isLoading
            }.collect { isLoading ->
                loadingView.isVisible = isLoading
            }
        }

        lifecycleScope.launch {
            viewModel.questionsState.collect { state ->
                state.questionList.let { questionList ->
                    questionsAdapter.submitList(questionList)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.categoriesState.collect { state ->
                state.plantCategoriesList?.let { plantCategoryList ->
                    plantCategoriesAdapter.submitList(plantCategoryList)
                }
            }
        }
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                plantCategoriesAdapter.submitList(it)
            }
        }
    }
}
