package com.zows.hubxrecruitmentcase.presentation.paywall

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentPaywallBinding

class PaywallFragment : Fragment(R.layout.fragment_paywall) {

    private val binding by viewBinding(FragmentPaywallBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pulseAnimation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.pulse_animation)

        with(binding) {
            layoutPremium.btnNext.startAnimation(pulseAnimation)

            ivClose.setOnClickListener {
                findNavController().navigate(R.id.onBoardingToHome)
            }
        }

    }

    companion object {
        fun newInstance(): PaywallFragment {
            return PaywallFragment()
        }
    }
}