package com.example.imbdapp.Services
import com.example.imbdapp.Data.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieService {
    @GET("/3/discover/movie")
    suspend fun getMoviesData(@QueryMap params: HashMap<String, String> ): Response<Movies>
}