package com.google.assign.ui.details

import androidx.fragment.app.viewModels
import com.google.assign.base.BaseFragment
import com.google.assign.databinding.DetailFragmentBinding
import com.google.assign.ui.list.ListViewModel
import com.google.assign.utils.Util.loadData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding>(DetailFragmentBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onViewCreated() {
        viewModel.getCatById(sharedViewModel.cat.id)
        viewModel.getCatById.observe(viewLifecycleOwner) {
            val data = loadData(it, binding.loader)
            binding.cat = data
        }
    }

}
