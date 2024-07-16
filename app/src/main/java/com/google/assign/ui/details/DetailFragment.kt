package com.google.assign.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.assign.base.BaseFragment
import com.google.assign.databinding.DetailFragmentBinding

class DetailFragment : BaseFragment() {

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment
            user = sharedViewModel.user
        }
        return binding.root
    }

}
