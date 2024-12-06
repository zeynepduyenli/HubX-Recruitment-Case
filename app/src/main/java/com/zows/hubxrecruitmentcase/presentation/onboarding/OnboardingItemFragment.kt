package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentOnboardingItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingItemFragment : Fragment(R.layout.fragment_onboarding_item) {

    private val binding by viewBinding(FragmentOnboardingItemBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arguments?.let {
                layoutOnboardingTitle.tvMain.text = it.getString(ARG_TITLE_MAIN)
                layoutOnboardingTitle.tvHighlight.text = it.getString(ARG_TITLE_HIGHLIGHT)
                layoutOnboardingTitle.tvSubtext.text = it.getString(ARG_TITLE_SUBTEXT)
                layoutOnboardingTitle.tvDesc.text = it.getString(ARG_DESC)
                ivOnboarding.setImageResource(it.getInt(ARG_IMG))
            }

            if (layoutOnboardingTitle.tvSubtext.text.isNullOrBlank())
                layoutOnboardingTitle.tvSubtext.visibility = View.GONE
            if (layoutOnboardingTitle.tvMain.text.isNullOrBlank() &&
                layoutOnboardingTitle.tvHighlight.text.isNullOrBlank() &&
                layoutOnboardingTitle.tvSubtext.text.isNullOrBlank() &&
                layoutOnboardingTitle.tvDesc.text.isNullOrBlank()
            ) {
                layoutOnboardingTitle.tvMain.visibility = View.GONE
                layoutOnboardingTitle.tvHighlight.visibility = View.GONE
                layoutOnboardingTitle.tvSubtext.visibility = View.GONE
                layoutOnboardingTitle.tvDesc.visibility = View.GONE
                layoutOnboardingTitle.underlineCurve.visibility = View.GONE
                ivOnboarding.scaleType = ImageView.ScaleType.CENTER_CROP
            }

            if (arguments?.getBoolean(ARG_UNDERLINE_BOOLEAN) == true)
                layoutOnboardingTitle.underlineCurve.visibility = View.VISIBLE
            else
                layoutOnboardingTitle.underlineCurve.visibility = View.GONE

            if (arguments?.getString(ARG_TITLE_MAIN) == getString(R.string.onboarding_first_page_title_main)) {

                layoutOnboardingTitle.tvMain.setTextAppearance(R.style.RegularTitleTextAppearance)

            }
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