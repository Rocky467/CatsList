package com.google.assign.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun log(msg: String) {
    Log.d("dataHere", msg)
}

fun log(tag: String, msg: String) {
    Log.d(tag, msg)
}

@BindingAdapter("android:loadUrl")
fun loadUrl(view: ImageView, url: String) {
    view.load(url)
}

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}