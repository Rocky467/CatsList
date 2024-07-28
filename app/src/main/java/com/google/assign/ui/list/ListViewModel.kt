package com.google.assign.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.google.assign.network.Repository

class ListViewModel(private val repository: Repository) : ViewModel() {

    val users = repository.getUsersList().liveData.cachedIn(viewModelScope)

}
