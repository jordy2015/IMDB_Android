package com.example.imbdapp.Extensions

import com.example.imbdapp.Data.Movie
import com.example.imbdapp.Utilities.IMAGES_URL

fun Movie.getRating(): Float {
    return popularity.times(5).div(100).toFloat()
}

fun Movie.getPosterUrl(): String {
    return IMAGES_URL+posterPath
}

fun Movie.getBackdropUrl(): String {
    return IMAGES_URL+backdropPath
}