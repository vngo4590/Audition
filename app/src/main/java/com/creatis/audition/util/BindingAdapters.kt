package com.creatis.audition.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.creatis.audition.R
import com.creatis.audition.data.network.HeaderInterceptor


/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.VISIBLE else View.GONE
}

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if (it == null) View.GONE else View.VISIBLE
}


/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val glide = Glide.with(imageView.context)
    if (url != null) {
        val glideUrl = GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader("x-rapidapi-host", HeaderInterceptor.API_HOST)
                .addHeader("x-rapidapi-key", HeaderInterceptor.API_KEY)
                .build()
        )
        glide.load(glideUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.mipmap.gradienta)
            .into(imageView)
    } else {
        glide.load(R.drawable.ic_launcher_foreground).centerInside()
            .into(imageView)
    }
}