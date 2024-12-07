package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.hideIfAllTextIsBlank
import com.zows.hubxrecruitmentcase.common.setVisibilityBasedOnCondition
import com.zows.hubxrecruitmentcase.common.setVisibilityBasedOnMultipleConditions
import com.zows.hubxrecruitmentcase.common.setVisibilityBasedOnText
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentOnboardingItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingItemFragment : Fragment(R.layout.fragment_onboarding_item) {

    private val binding by viewBinding(FragmentOnboardingItemBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Can be written more readable imo */

        with(binding) {
            arguments?.let {
                layoutOnboardingTitle.tvMain.text = it.getString(ARG_TITLE_MAIN)
                layoutOnboardingTitle.tvHighlight.text = it.getString(ARG_TITLE_HIGHLIGHT)
                layoutOnboardingTitle.tvSubtext.text = it.getString(ARG_TITLE_SUBTEXT)
                layoutOnboardingTitle.tvDesc.text = it.getString(ARG_DESC)
                ivOnboarding.setImageResource(it.getInt(ARG_IMG))
            }

            listOf(
                layoutOnboardingTitle.tvMain,
                layoutOnboardingTitle.tvHighlight,
                layoutOnboardingTitle.tvSubtext,
                layoutOnboardingTitle.tvDesc
            ).hideIfAllTextIsBlank()

            val textViews = listOf(
                layoutOnboardingTitle.tvMain,
                layoutOnboardingTitle.tvHighlight,
                layoutOnboardingTitle.tvSubtext
            )

            ivOnboarding.scaleType = ImageView.ScaleType.CENTER_CROP

            textViews.setVisibilityBasedOnMultipleConditions(layoutPremium.clPremium, ivOnboarding)

            layoutPremium.ivClose.setOnClickListener {
                findNavController().navigate(R.id.onBoardingToHome)
            }

            when (arguments?.getString(ARG_TITLE_MAIN)) {
                getString(R.string.onboarding_first_page_title_main) -> {
                    layoutOnboardingTitle.tvMain.setTextAppearance(R.style.RegularTitleTextAppearance)
                    layoutOnboardingTitle.tvHighlight.setTextAppearance(R.style.MediumTitleTextAppearance)
                }

                getString(R.string.onboarding_second_page_title_main), getString(R.string.onboarding_third_page_title_main) -> {
                    layoutOnboardingTitle.tvMain.setTextAppearance(R.style.MediumTitleTextAppearance)
                    layoutOnboardingTitle.tvHighlight.setTextAppearance(R.style.BoldTitleTextAppearance)
                    layoutOnboardingTitle.tvSubtext.setTextAppearance(R.style.MediumTitleTextAppearance)
                }
            }

            layoutOnboardingTitle.tvSubtext.setVisibilityBasedOnText()
            layoutOnboardingTitle.underlineCurve.setVisibilityBasedOnCondition(
                arguments?.getBoolean(
                    ARG_UNDERLINE_BOOLEAN
                ) == true
            )
        }
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
        ) =
            OnboardingItemFragment().apply {
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