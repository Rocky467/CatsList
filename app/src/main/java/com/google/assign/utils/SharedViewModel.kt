package com.google.assign.utils

import androidx.lifecycle.ViewModel
import com.google.assign.model.Cats

class SharedViewModel : ViewModel() {
    var cat = Cats()
}