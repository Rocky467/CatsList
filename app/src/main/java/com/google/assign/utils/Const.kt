@file:Suppress("DEPRECATION")

package com.google.assign.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

const val BASE_URL = "https://random-data-api.com/api/users/"

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.isConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}
