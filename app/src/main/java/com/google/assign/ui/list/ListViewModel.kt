package com.google.assign.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.google.assign.model.User
import com.google.assign.network.Repository
import com.google.assign.utils.Resource
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository) : ViewModel() {

    val userList = repository.getUsersList().liveData.cachedIn(viewModelScope)

    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>> get() = _user

    fun getUserDetails() = viewModelScope.launch {
        _user.postValue(Resource.Loading())
        _user.postValue(repository.getUser())
    }

}
