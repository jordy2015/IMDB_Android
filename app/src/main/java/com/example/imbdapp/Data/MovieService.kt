package com.example.imbdapp.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {
    @GET("/3/discover/movie")
    suspend fun getMoviesData(@QueryMap params: HashMap<String, String> ): Response<Movies>
}