package com.google.assign.ui.details

import com.google.assign.base.BaseFragment
import com.google.assign.databinding.DetailFragmentBinding

class DetailFragment : BaseFragment<DetailFragmentBinding>(DetailFragmentBinding::inflate) {

    override fun onCreateView() {
        binding.lifecycleOwner = this@DetailFragment
        binding.user = sharedViewModel.user
    }

}
