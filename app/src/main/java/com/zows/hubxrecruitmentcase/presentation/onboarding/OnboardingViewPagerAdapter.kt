package com.zows.hubxrecruitmentcase.presentation.onboarding

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zows.hubxrecruitmentcase.R

class OnboardingViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            START_SCREEN -> OnboardingItemFragment.newInstance(
                title = context.resString(R.string.onboarding_first_page_title_main),
                highlight = context.resString(R.string.onboarding_first_page_title_highlight),
                subtext = null,
                underlineEnabled = false,
                desc = context.resString(R.string.onboarding_first_page_desc),
                img = R.drawable.img_onboarding_start_screen
            )

            FIRST_SCREEN -> OnboardingItemFragment.newInstance(
                title = context.resString(R.string.onboarding_second_page_title_main),
                highlight = context.resString(R.string.onboarding_second_page_title_highlight),
                subtext = context.resString(R.string.onboarding_second_page_title_subtext),
                underlineEnabled = true,
                desc = null,
                img = R.drawable.img_onboarding_second_screen
            )

            SECOND_SCREEN -> OnboardingItemFragment.newInstance(
                title = context.resString(R.string.onboarding_third_page_title_main),
                highlight = context.resString(R.string.onboarding_third_page_title_highlight),
                subtext = null,
                underlineEnabled = true,
                desc = null,
                img = R.drawable.img_onboarding_third_screen
            )

            PAYWALL_SCREEN -> OnboardingItemFragment.newInstance(
                title = null,
                highlight = null,
                subtext = null,
                underlineEnabled = false,
                desc = null,
                img = R.drawable.img_paywall
            )

            else -> throw IllegalArgumentException("Invalid position: $position")

        }
    }

    private fun Context.resString(resId: Int) = ContextCompat.getString(this, resId)

    override fun getItemCount() = 4

    companion object {
        private const val START_SCREEN = 0
        private const val FIRST_SCREEN = 1
        private const val SECOND_SCREEN = 2
        private const val PAYWALL_SCREEN = 3
    }
}