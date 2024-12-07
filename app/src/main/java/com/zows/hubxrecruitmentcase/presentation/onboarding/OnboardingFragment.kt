package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

    private val drawables = intArrayOf(
        R.drawable.onboarding_bg_vibrant,
        R.drawable.onboarding_bg_subtle
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewPager()
    }

    private fun setupUI() {
        val pulseAnimation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.pulse_animation)
        binding.btnPulse.startAnimation(pulseAnimation)

        binding.btnNext.setOnClickListener { onNextButtonClicked() }
    }

    private fun setupViewPager() {
        with(binding) {
            viewPager.isUserInputEnabled = false
            viewPager.adapter = OnboardingViewPagerAdapter(requireActivity(), requireContext())
            viewPager.offscreenPageLimit = 1

            viewPager.registerOnPageChangeCallback(createPageChangeCallback())

            TabLayoutMediator(pageIndicator, viewPager) { tab, position ->
                tab.view.visibility = if (position in 1..3) View.VISIBLE else View.GONE
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

            else -> showPaywallScreen()
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
            tvTerms.visibility = if (showTerms) View.VISIBLE else View.INVISIBLE
            pageIndicator.visibility = if (showPageIndicator) View.VISIBLE else View.INVISIBLE
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

    private fun onNextButtonClicked() {
        val lastPageIndex = binding.viewPager.adapter?.itemCount?.minus(1) ?: 0
        if (getItem() == lastPageIndex) {
            navigateToHome()
        } else {
            goToNextPage()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.onBoardingToHome)
    }

    private fun goToNextPage() {
        binding.viewPager.setCurrentItem(getItem() + 1, true)
    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }
}
