package com.zows.hubxrecruitmentcase.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zows.hubxrecruitmentcase.R
import com.zows.hubxrecruitmentcase.common.viewBinding
import com.zows.hubxrecruitmentcase.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}