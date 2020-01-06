package com.example.imbdapp.Data

import com.squareup.moshi.Json

data class Movie (
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    val title: String,
    val popularity: Double,
    val id: Int
)