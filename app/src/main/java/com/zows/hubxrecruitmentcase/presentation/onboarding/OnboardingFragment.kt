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

        with(binding) {

            val pulseAnimation: Animation =
                AnimationUtils.loadAnimation(context, R.anim.pulse_animation)
            btnNext.startAnimation(pulseAnimation)
            viewPager.isUserInputEnabled = false
            viewPager.adapter = OnboardingViewPagerAdapter(requireActivity(), requireContext())
            viewPager.offscreenPageLimit = 1
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            clOnboarding.setBackgroundResource(drawables[0])

                            pageIndicator.visibility = View.INVISIBLE
                            btnNext.text = getString(R.string.get_started)
                            tvTerms.visibility = View.VISIBLE
                            requireActivity().window.setStatusBarTextColor(isLightText = false)
                        }

                        1 -> {
                            clOnboarding.setBackgroundResource(drawables[1])
                            pageIndicator.visibility = View.VISIBLE
                            btnNext.text = getString(R.string.btn_continue)
                            tvTerms.visibility = View.INVISIBLE
                            requireActivity().window.setStatusBarTextColor(isLightText = false)
                        }

                        2 -> {
                            clOnboarding.setBackgroundResource(drawables[1])
                            pageIndicator.visibility = View.VISIBLE
                            btnNext.text = getString(R.string.btn_continue)
                            tvTerms.visibility = View.INVISIBLE
                            requireActivity().window.setStatusBarTextColor(isLightText = false)
                        }

                        else -> { // Paywall Screen
                            btnNext.clearAnimation()
                            btnNext.visibility = View.GONE
                            frameFooter.visibility = View.GONE
                            requireActivity().window.setStatusBarTextColor(isLightText = true)
                        }


                    }
                }

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