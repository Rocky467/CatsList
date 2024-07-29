package com.google.assign.utils

import androidx.lifecycle.ViewModel
import com.google.assign.model.User

class SharedViewModel : ViewModel() {
    var user = User()
}