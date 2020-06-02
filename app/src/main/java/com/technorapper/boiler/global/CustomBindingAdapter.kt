package com.technorapper.boiler.global

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.technorapper.boiler.R
import com.squareup.picasso.Picasso


class CustomBindingAdapter(private val picasso: Picasso) {

   /* @BindingAdapter("android:src_url")
    fun loadImage(view: ImageView, url: String?) {
        if (url == null || url.equals("", ignoreCase = true)) {
            view.setImageResource(R.mipmap.ic_launcher)
            return
        }
        picasso.load(url).error(R.mipmap.ic_launcher).fit().into(view)
    }*/
}