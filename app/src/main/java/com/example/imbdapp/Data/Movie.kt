package com.example.imbdapp.Data

import com.example.imbdapp.Utilities.IMAGES_URL
import com.squareup.moshi.Json

data class Movie (
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    val title: String,
    val id: Int,
    val overview: String
){
    val posterURL get() = "$IMAGES_URL$posterPath"
}