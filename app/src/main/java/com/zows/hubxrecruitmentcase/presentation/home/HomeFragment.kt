package com.zows.hubxrecruitmentcase.presentation.home

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.setStatusBarTextColor
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentHomeBinding
import com.zows.hubxrecruitmentcase.presentation.paywall.GridSpacingItemDecoration
import com.zows.hubxrecruitmentcase.presentation.paywall.PaywallFragment
import com.zows.hubxrecruitmentcase.presentation.paywall.SpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val questionsAdapter by lazy { QuestionsAdapter(emptyList()) }
    private val categoriesAdapter by lazy { CategoriesAdapter(emptyList()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.setStatusBarTextColor(isLightText = false)
        setupRecyclerView()
        initPremiumTitleShader()
        viewModel.getQuestions()
        viewModel.getCategories()
        initObserver()

        with(binding){
            premiumCard.setOnClickListener { findNavController().navigate(R.id.homeToPaywall) }
        }
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerViewQuestions.adapter = questionsAdapter
        recyclerViewQuestions.setHasFixedSize(true)
        val space = resources.getDimensionPixelSize(R.dimen.spacing_medium)
        recyclerViewQuestions.addItemDecoration(SpacingItemDecoration(space))

        val spaceGrid = resources.getDimensionPixelSize(R.dimen.spacing_small)
        recyclerViewCategories.adapter = categoriesAdapter
        recyclerViewCategories.setHasFixedSize(true)
        recyclerViewCategories.addItemDecoration(GridSpacingItemDecoration(2, spaceGrid, includeEdge = true))
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
        viewModel.questionsState.observe(viewLifecycleOwner) { state ->
            state.questionList?.let { questionList ->
                questionsAdapter.updateList(questionList)
            }
        }

        viewModel.categoriesState.observe(viewLifecycleOwner) { state ->
            state.categoriesList?.let { categoryList ->
                categoriesAdapter.updateList(categoryList)
            }
        }
    }

}
