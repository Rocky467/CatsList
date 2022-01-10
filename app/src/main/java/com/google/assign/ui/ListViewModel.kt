package com.google.assign.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.google.assign.network.Repository

class ListViewModel(private val repository: Repository) : ViewModel() {

    // .liveData or .flow decide here
    val users = repository.usersList.flow.cachedIn(viewModelScope)

}
