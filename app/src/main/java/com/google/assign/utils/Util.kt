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

@Suppress("unused")
fun delays(millis: Long, function: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        function()
    }, millis)
}

@BindingAdapter("android:loadUrl")
fun loadUrl(imageView: ImageView, url: String?) {
    url?.let {
        imageView.load(it)
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