package com.example.imbdapp.extensions

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.startAnimation() {
    max = 10
    visibility = View.VISIBLE
}

fun ProgressBar.stopAnimation() {
    max = 0
    visibility = View.GONE
}