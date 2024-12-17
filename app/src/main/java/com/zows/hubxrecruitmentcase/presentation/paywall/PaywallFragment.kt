package com.zows.hubxrecruitmentcase.presentation.paywall

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentPaywallBinding
import com.zows.hubxrecruitmentcase.domain.model.CardItem

class PaywallFragment : Fragment(R.layout.fragment_paywall) {

    private val binding by viewBinding(FragmentPaywallBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val fromScreen = arguments?.getString(ARG_FROM_SCREEN) ?: "home"
            ivClose.setOnClickListener {
                when (fromScreen) {
                    "home" -> findNavController().navigate(R.id.action_paywallToHome)
                    "onboarding" -> findNavController().navigate(R.id.action_onBoardingToHome)
                    else -> Log.d("PaywallFragment", "Navigated from: $fromScreen")
                }
            }

            val recyclerView = recyclerViewPremiumCards

            val cardItems = listOf(
                CardItem("Unlimited", "Plant Identify", R.drawable.ic_scanner),
                CardItem("Faster", "Process", R.drawable.ic_speedometer),
                CardItem("Detailed", "Plant Care", R.drawable.ic_herbal_treatment)
            )

            recyclerView.adapter = CardAdapter(cardItems)

            val space = resources.getDimensionPixelSize(R.dimen.spacing_medium)
            recyclerView.addItemDecoration(SpacingItemDecoration(space))

            val subscriptionButtons = listOf(
                btnSubscriptionRegular.cardRegularSubscription,
                btnSubscriptionHighlighted.cardHighlightedSubscription
            )

            subscriptionButtons[1].isSelected = true

            subscriptionButtons.forEachIndexed { index, materialCardView ->
                materialCardView.setOnClickListener {
                    if (!materialCardView.isSelected) {
                        // Deselect all other cards
                        subscriptionButtons.forEachIndexed { otherIndex, otherCard ->
                            if (otherIndex != index) {
                                otherCard.isSelected = false
                                // Update the radio button image for the deselected card
                                val otherRadioButton = when (otherIndex) {
                                    0 -> btnSubscriptionRegular.radioButton
                                    1 -> btnSubscriptionHighlighted.radioButton
                                    else -> null
                                } ?: return@forEachIndexed

                                otherRadioButton.isSelected = false
                                otherRadioButton.setImageResource(R.drawable.ic_subscription_deselected)
                            }
                        }

                        // Select the current card and update the radio button image
                        materialCardView.isSelected = true
                        val radioButton = when (index) {
                            0 -> btnSubscriptionRegular.radioButton
                            1 -> btnSubscriptionHighlighted.radioButton
                            else -> null
                        } ?: return@setOnClickListener

                        radioButton.isSelected = true
                        radioButton.setImageResource(R.drawable.ic_subscription_selected)
                    }
                }
            }

        }
    }


    companion object {
        private const val ARG_FROM_SCREEN = "fromScreen"

        fun newInstance(fromScreen: String): PaywallFragment {
            val fragment = PaywallFragment()
            val args = Bundle()
            args.putString(ARG_FROM_SCREEN, fromScreen)
            fragment.arguments = args
            return fragment
        }
    }
}