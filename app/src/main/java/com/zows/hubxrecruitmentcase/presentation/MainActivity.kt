package com.zows.hubxrecruitmentcase.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.ActivityMainBinding
import com.zows.hubxrecruitmentcase.presentation.home.HomeFragment
import com.zows.hubxrecruitmentcase.presentation.onboarding.OnboardingFragment
import com.zows.hubxrecruitmentcase.presentation.paywall.PaywallFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.fragmentContainerView.post {
            navController = findNavController(binding.fragmentContainerView.id)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                binding.bottomNav.isVisible = destination.id != R.id.onBoardingFragment
                binding.floatingActionButton.isVisible = destination.id != R.id.onBoardingFragment
            }
        }
    }

}