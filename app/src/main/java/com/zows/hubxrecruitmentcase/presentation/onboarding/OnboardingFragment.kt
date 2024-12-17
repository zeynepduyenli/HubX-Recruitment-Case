package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.setStatusBarTextColor
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel by viewModels<OnboardingViewModel>()

    private val drawables = intArrayOf(
        R.drawable.onboarding_bg_vibrant,
        R.drawable.onboarding_bg_subtle
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isOnboardingCompleted.observe(viewLifecycleOwner) { isCompleted ->
            if (isCompleted) {
                findNavController().navigate(R.id.action_onBoardingToHome)
            }
        }
        setupUI()
        setupViewPager()
        requireActivity().window.setStatusBarTextColor(isLightText = false)
    }

    private fun setupUI() {
        val bubbleAnimation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.bubble_animation)
        binding.btnBubble.startAnimation(bubbleAnimation)
        binding.btnNext.setOnClickListener { goToNextPage() }
    }

    private fun setupViewPager() {
        with(binding) {
            viewPager.isUserInputEnabled = false
            viewPager.isFakeDragging
            viewPager.adapter = OnboardingViewPagerAdapter(requireActivity(), requireContext())
            viewPager.offscreenPageLimit = 1
            viewPager.registerOnPageChangeCallback(createPageChangeCallback())

            TabLayoutMediator(pageIndicator, viewPager) { tab, position ->
                tab.view.isVisible = position in 1..3
                tab.view.isClickable = false;
            }.attach()

        }
    }

    private fun createPageChangeCallback() = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateUIForPage(position)
        }
    }

    private fun updateUIForPage(position: Int) {
        when (position) {
            0 -> configureScreen(
                background = drawables[0],
                buttonText = getString(R.string.get_started),
                showTerms = true,
                showPageIndicator = false,
            )

            1, 2 -> configureScreen(
                background = drawables[1],
                buttonText = getString(R.string.btn_continue),
                showTerms = false,
                showPageIndicator = true,
            )

            else -> {
                showPaywallScreen()
                viewModel.completeOnboarding()
            }
        }
    }

    private fun configureScreen(
        background: Int,
        buttonText: String,
        showTerms: Boolean,
        showPageIndicator: Boolean,
    ) {
        with(binding) {
            clOnboarding.setBackgroundResource(background)
            btnNext.text = buttonText
            tvTerms.isInvisible = !showTerms
            pageIndicator.isInvisible = !showPageIndicator
        }
    }

    private fun showPaywallScreen() {
        with(binding) {
            btnNext.clearAnimation()
            btnNext.visibility = View.GONE
            frameFooter.visibility = View.GONE
            requireActivity().window.setStatusBarTextColor(isLightText = true)
        }
    }

    private fun goToNextPage() =
        binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
}

@Entity(tableName = "onboarding_status")
data class OnboardingStatus(
    @PrimaryKey val id: Int = 0,
    val isOnboardCompleted: Boolean = false
)
