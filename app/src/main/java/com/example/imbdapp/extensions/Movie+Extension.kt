package com.example.imbdapp.extensions

import com.example.imbdapp.models.Movie
import com.example.imbdapp.utilities.IMAGES_URL

fun Movie.getRating(): Float {
    return voteAverage.div(2).toFloat() //scale 1 to 10
}

fun Movie.getPosterUrl(): String {
    return IMAGES_URL+posterPath
}

fun Movie.getBackdropUrl(): String {
    return IMAGES_URL+backdropPath
}