package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
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
                tvTitle.text = it.getString(ARG_TITLE)
                tvDesc.text = it.getString(ARG_DESC)
                ivOnboarding.setImageResource(it.getInt(ARG_IMG))
            }

            if (tvTitle.text.isNullOrBlank())
                tvTitle.visibility = View.GONE
            else
                tvTitle.visibility = View.VISIBLE

            if (tvDesc.text.isNullOrBlank())
                tvDesc.visibility = View.GONE
            else
                tvDesc.visibility = View.VISIBLE
        }
    }

    companion object {

        private const val ARG_TITLE = "param1"
        private const val ARG_DESC = "param2"
        private const val ARG_IMG = "param3"

        @JvmStatic
        fun newInstance(title: String?, desc: String?, img: Int?) =
            OnboardingItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESC, desc)
                    img?.let { putInt(ARG_IMG, it) }
                }
            }
    }
}