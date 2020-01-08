package com.example.imbdapp.utilities

const val BASE_URL = "https://api.themoviedb.org/"

const val IMAGES_URL = "https://image.tmdb.org/t/p/w500"

const val API_KEY_STRING = "f2b566ae4212940dc51544f3986bd3ca"

enum class ParamsEmun(val string: String) {
    KEY("api_key"),
    YEAR("primary_release_year"),
    PAGE("page"),
    QUERY("query")
}