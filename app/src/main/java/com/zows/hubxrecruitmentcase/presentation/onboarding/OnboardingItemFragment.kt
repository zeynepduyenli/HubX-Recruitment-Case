package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.setVisibilityBasedOnCondition
import com.zows.hubxrecruitmentcase.common.setVisibilityBasedOnText
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentOnboardingItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingItemFragment : Fragment(R.layout.fragment_onboarding_item) {

    private val binding by viewBinding(FragmentOnboardingItemBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        arguments?.let {
            // Set the text and image for the onboarding layout
            setUpOnboardingText(it)
            setUpOnboardingImage(it)

            // Apply animation to the "Next" button
            startPulseAnimation()

            // Configure the style based on the title of the page
            configurePageStyle(it)

            // Handle visibility of elements based on conditions
            handleVisibilityBasedOnArguments(it)
        }
    }

    private fun setUpOnboardingText(arguments: Bundle) {
        with(binding.layoutOnboardingTitle) {
            tvMain.text = arguments.getString(ARG_TITLE_MAIN)
            tvHighlight.text = arguments.getString(ARG_TITLE_HIGHLIGHT)
            tvSubtext.text = arguments.getString(ARG_TITLE_SUBTEXT)
            tvDesc.text = arguments.getString(ARG_DESC)
        }
    }

    private fun setUpOnboardingImage(arguments: Bundle) {
        binding.ivOnboarding.setImageResource(arguments.getInt(ARG_IMG))
    }

    private fun startPulseAnimation() {
        val pulseAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.pulse_animation)
        binding.layoutPremium.btnNext.startAnimation(pulseAnimation)
    }

    private fun configurePageStyle(arguments: Bundle) {
        val titleMain = arguments.getString(ARG_TITLE_MAIN)

        when (titleMain) {
            getString(R.string.onboarding_first_page_title_main) -> {
                styleForFirstPage()
            }

            getString(R.string.onboarding_second_page_title_main),
            getString(R.string.onboarding_third_page_title_main) -> {
                styleForSubsequentPages()
            }
        }
    }

    private fun styleForFirstPage() {
        with(binding.layoutOnboardingTitle) {
            tvMain.setTextAppearance(R.style.RegularTitleTextAppearance)
            tvHighlight.setTextAppearance(R.style.MediumTitleTextAppearance)
        }
        binding.ivOverlay.visibility = View.INVISIBLE
    }

    private fun styleForSubsequentPages() {
        with(binding.layoutOnboardingTitle) {
            tvMain.setTextAppearance(R.style.MediumTitleTextAppearance)
            tvHighlight.setTextAppearance(R.style.BoldTitleTextAppearance)
            tvSubtext.setTextAppearance(R.style.MediumTitleTextAppearance)
        }
        binding.ivOverlay.visibility = View.VISIBLE
    }

    private fun handleVisibilityBasedOnArguments(arguments: Bundle) {
        binding.layoutOnboardingTitle.tvSubtext.setVisibilityBasedOnText()
        val isUnderlineEnabled = arguments.getBoolean(ARG_UNDERLINE_BOOLEAN)
        binding.layoutOnboardingTitle.underlineCurve.setVisibilityBasedOnCondition(isUnderlineEnabled)
    }

    companion object {
        private const val ARG_TITLE_MAIN = "param1"
        private const val ARG_TITLE_HIGHLIGHT = "param2"
        private const val ARG_TITLE_SUBTEXT = "param3"
        private const val ARG_UNDERLINE_BOOLEAN = "param4"
        private const val ARG_DESC = "param5"
        private const val ARG_IMG = "param6"

        @JvmStatic
        fun newInstance(
            title: String?,
            highlight: String?,
            subtext: String?,
            underlineEnabled: Boolean,
            desc: String?,
            img: Int?
        ) = OnboardingItemFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE_MAIN, title)
                putString(ARG_TITLE_HIGHLIGHT, highlight)
                putString(ARG_TITLE_SUBTEXT, subtext)
                putBoolean(ARG_UNDERLINE_BOOLEAN, underlineEnabled)
                putString(ARG_DESC, desc)
                img?.let { putInt(ARG_IMG, it) }
            }
        }
    }
}
