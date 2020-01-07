package com.example.imbdapp.Extensions

import com.example.imbdapp.Data.Movie
import com.example.imbdapp.Utilities.IMAGES_URL

fun Movie.getRating(): Float {
    return voteAverage.div(2).toFloat() //scale 1 to 10
}

fun Movie.getPosterUrl(): String {
    return IMAGES_URL+posterPath
}

fun Movie.getBackdropUrl(): String {
    return IMAGES_URL+backdropPath
}