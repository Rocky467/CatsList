package com.google.assign.utils

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.assign.R

fun log(tag: String, msg: Any?) {
    Log.d(tag, "$msg")
}

fun delay(sec: Long, function: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        function()
    }, sec * 1000)
}

@BindingAdapter("android:loadUrl")
fun loadUrl(view: ImageView, url: String?) {
    if (url != null) {
        view.load(url)
    }
}

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.default_placeholder)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}