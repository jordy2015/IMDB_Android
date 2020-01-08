package com.example.imbdapp.models

import com.squareup.moshi.Json

data class Movies (@Json(name = "total_pages") val lastPage: Int, val results: List<Movie>)