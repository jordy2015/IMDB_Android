package com.example.imbdapp.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")
fun loadImage(view: ImageView, imageURL: String){
    Glide.with(view.context).load(imageURL).into(view)
}