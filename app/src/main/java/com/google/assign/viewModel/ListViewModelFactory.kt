package com.google.assign.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.assign.api.ApiService

class ListViewModelFactory(
    private val apiService: ApiService,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
