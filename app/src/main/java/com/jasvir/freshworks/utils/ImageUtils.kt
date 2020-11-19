package com.jasvir.freshworks.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageUtils {
    companion object {
        fun loadImage(url: String, imageView: ImageView, context: Context) {
            Glide.with(context)
                .load(url)
                .thumbnail(.2f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}