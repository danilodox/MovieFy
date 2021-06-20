package com.br.moviefy.util

import android.view.View

fun View.isVisible(isVisible: Boolean) {
    this.visibility = if(isVisible) View.VISIBLE else {
        View.GONE
    }
}