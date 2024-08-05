package com.google.assign.ui.details

import com.google.assign.base.BaseFragment
import com.google.assign.databinding.DetailFragmentBinding
import com.google.assign.ui.list.ListViewModel
import com.google.assign.utils.Util.log
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
            val data = takeIfSuccess(it, binding.loader)
            log("TAG", data?.avatar)
        }
    }

}
