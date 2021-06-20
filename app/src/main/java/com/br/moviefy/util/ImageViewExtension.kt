package com.br.moviefy.util

import android.widget.ImageView
import com.br.moviefy.R
import com.bumptech.glide.Glide

const val POSTER_IMAGE_PATH_PREFIX = "https://image.tmdb.org/t/p/w300"

fun ImageView.loadImage(url: String?) {

    if (!url.isNullOrBlank()) {
        Glide.with(this)
                .load("${POSTER_IMAGE_PATH_PREFIX}${url}")
                .error(R.drawable.no_internet_24dp)
                .into(this)
    } else {
        this.setImageResource(R.drawable.ic_error)
    }



}





