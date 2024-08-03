package com.google.assign.ui.details

import androidx.core.view.isVisible
import com.google.assign.base.BaseFragment
import com.google.assign.databinding.DetailFragmentBinding
import com.google.assign.ui.list.ListViewModel
import com.google.assign.utils.Resource
import com.google.assign.utils.log
import com.google.assign.utils.showError
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailFragmentBinding>(DetailFragmentBinding::inflate) {

    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView() {
        binding.lifecycleOwner = this@DetailFragment
        binding.user = sharedViewModel.user
    }

    override fun onViewCreated() {
        viewModel.getUserDetails()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.loader.isVisible = false
            when (it) {
                is Resource.Loading -> {
                    binding.loader.isVisible = true
                }

                is Resource.Success -> {
                    log("userdata", it.data)
                }

                is Resource.Error -> {
                    binding.root.showError(it.error.toString())
                }
            }
        }
    }

}
