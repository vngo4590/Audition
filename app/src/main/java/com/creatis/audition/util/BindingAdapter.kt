package com.creatis.audition.util


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.creatis.audition.R
import com.bumptech.glide.load.model.LazyHeaders

import com.bumptech.glide.load.model.GlideUrl
import com.creatis.audition.data.network.HeaderInterceptor


/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val glideUrl = GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader("x-rapidapi-host", HeaderInterceptor.API_HOST)
            .addHeader("x-rapidapi-key", HeaderInterceptor.API_KEY)
            .build()
    )
    Glide.with(imageView.context).load(glideUrl)
        .placeholder(R.drawable.ic_launcher_background)
        .centerCrop()
        .error(R.drawable.ic_dashboard_black_24dp)
        .into(imageView)
}
