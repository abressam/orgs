package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.tryLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro) // if the image was null
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}