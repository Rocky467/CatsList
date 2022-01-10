package com.google.assign.utils

import android.util.Log


fun log(msg: String) {
    Log.d("logs", msg)
}

fun log(tag: String, msg: String) {
    Log.d(tag, msg)
}
