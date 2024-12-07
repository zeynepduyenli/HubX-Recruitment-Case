package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            viewPager.adapter = OnboardingViewPagerAdapter(requireActivity(), requireContext())
            viewPager.offscreenPageLimit = 1

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> { // Start Screen
                            pageIndicator.visibility = View.INVISIBLE
                            btnNext.text = getString(R.string.get_started)
                            tvTerms.visibility = View.VISIBLE
                            clOnboarding.setBackgroundResource(R.drawable.onboarding_background)
                            requireActivity().window.setStatusBarTextColor(isLightText = false)
                            ivOverlay.visibility = View.GONE
                            tvChargeNote.visibility = View.INVISIBLE
                            tvChargeFooter.visibility = View.INVISIBLE
                        }

                        2 -> {
                            pageIndicator.visibility = View.VISIBLE
                            btnNext.text = getString(R.string.btn_continue)
                            tvTerms.visibility = View.INVISIBLE
                            clOnboarding.setBackgroundResource(R.drawable.onboarding_background)
                            requireActivity().window.setStatusBarTextColor(isLightText = false)
                            ivOverlay.visibility = View.VISIBLE
                            tvChargeNote.visibility = View.INVISIBLE
                            tvChargeFooter.visibility = View.INVISIBLE
                        }

                        3 -> { // Paywall Screen
                            pageIndicator.visibility = View.INVISIBLE
                            btnNext.text = getString(R.string.onboarding_trial_button)
                            tvTerms.visibility = View.INVISIBLE
                            clOnboarding.background = null
                            clOnboarding.setBackgroundColor(
                                resources.getColor(R.color.dark_green, null)
                            )
                            ivOverlay.visibility = View.GONE
                            requireActivity().window.setStatusBarTextColor(isLightText = true)
                            ivOverlay.visibility = View.GONE
                            tvChargeNote.visibility = View.VISIBLE
                            tvChargeFooter.visibility = View.VISIBLE
                        }

                        else -> { // Middle Screens
                            pageIndicator.visibility = View.VISIBLE
                            btnNext.text = getString(R.string.btn_continue)
                            tvTerms.visibility = View.INVISIBLE
                            clOnboarding.setBackgroundResource(R.drawable.onboarding_background)
                            requireActivity().window.setStatusBarTextColor(isLightText = false)
                            ivOverlay.visibility = View.GONE
                            tvChargeNote.visibility = View.INVISIBLE
                            tvChargeFooter.visibility = View.INVISIBLE
                        }
                    }
                }


                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) = Unit
                override fun onPageScrollStateChanged(arg0: Int) = Unit
            })

            TabLayoutMediator(pageIndicator, viewPager) { tab, position ->
                when (position) {
                    1 -> tab.view.visibility = View.VISIBLE
                    2 -> tab.view.visibility = View.VISIBLE
                    3 -> tab.view.visibility = View.VISIBLE
                    else -> tab.view.visibility = View.GONE
                }
            }.attach()


            btnNext.setOnClickListener {
                val lastPageIndex = viewPager.adapter?.itemCount?.minus(1) ?: 0
                if (getItem() == lastPageIndex) {
                    findNavController().navigate(R.id.onBoardingToHome)
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }
        }
    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }
}