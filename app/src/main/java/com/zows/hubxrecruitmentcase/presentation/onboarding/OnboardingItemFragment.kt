package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentOnboardingItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingItemFragment : Fragment(R.layout.fragment_onboarding_item) {

    private val binding by viewBinding(FragmentOnboardingItemBinding::bind)

    private val pageIndex by lazy { arguments?.getInt(ARG_INDEX) ?: 0 }
    private val titleMain by lazy { arguments?.getString(ARG_TITLE_MAIN).orEmpty() }
    private val titleHighlight by lazy { arguments?.getString(ARG_TITLE_HIGHLIGHT).orEmpty() }
    private val subtext by lazy { arguments?.getString(ARG_TITLE_SUBTEXT).orEmpty() }
    private val underlineEnabled by lazy { arguments?.getBoolean(ARG_UNDERLINE_BOOLEAN) ?: false }
    private val desc by lazy { arguments?.getString(ARG_DESC).orEmpty() }
    private val image by lazy { arguments?.getInt(ARG_IMG) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        setUpOnboardingText()
        setUpOnboardingImage()
        configurePageStyle()
        handleVisibilityBasedOnArguments()
    }

    private fun setUpOnboardingText() {
        with(binding.layoutOnboardingTitle) {
            tvMain.text = titleMain
            tvHighlight.text = titleHighlight
            tvSubtext.text = subtext
            tvDesc.text = desc
        }
    }

    private fun setUpOnboardingImage() {
        image?.let {
            binding.ivOnboarding.setImageResource(it)
        }
    }

    private fun configurePageStyle() {
        when (pageIndex) {
            0 -> styleForFirstPage()
            1, 2 -> styleForOtherPages()
        }
    }

    private fun styleForFirstPage() {
        with(binding.layoutOnboardingTitle) {
            tvMain.setTextAppearance(R.style.RegularTitleTextAppearance)
            tvHighlight.setTextAppearance(R.style.BoldTitleTextAppearance)
        }
        binding.ivOverlay.visibility = View.INVISIBLE
    }

    private fun styleForOtherPages() {
        with(binding.layoutOnboardingTitle) {
            tvMain.setTextAppearance(R.style.MediumTitleTextAppearance)
            tvHighlight.setTextAppearance(R.style.BoldTitleTextAppearance)
        }
        binding.ivOverlay.visibility = View.VISIBLE
    }

    private fun handleVisibilityBasedOnArguments() {
        binding.layoutOnboardingTitle.tvSubtext.isVisible = subtext.isNotEmpty()
        binding.layoutOnboardingTitle.underlineCurve.isVisible = underlineEnabled
    }

    companion object {
        private const val ARG_INDEX = "index"
        private const val ARG_TITLE_MAIN = "param1"
        private const val ARG_TITLE_HIGHLIGHT = "param2"
        private const val ARG_TITLE_SUBTEXT = "param3"
        private const val ARG_UNDERLINE_BOOLEAN = "param4"
        private const val ARG_DESC = "param5"
        private const val ARG_IMG = "param6"

        @JvmStatic
        fun newInstance(
            index: Int,
            title: String?,
            highlight: String?,
            subtext: String?,
            underlineEnabled: Boolean,
            desc: String?,
            img: Int?
        ) = OnboardingItemFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_INDEX, index)
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
