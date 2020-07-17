package com.oliverdumhart.plantcare

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("picture")
fun ImageView.setPicture(bitmap: Bitmap?) {
    bitmap?.let {
        this.setImageBitmap(bitmap)
    }
}