package com.google.assign.utils

import android.util.Log

const val BASE_URL = "https://random-data-api.com/api/users/"

fun log(msg: String) {
    Log.d("log", msg)
}

fun log(tag: String, msg: String) {
    Log.d(tag, msg)
}