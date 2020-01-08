package com.example.imbdapp.services
import com.example.imbdapp.models.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieService {
    @GET("/3/discover/movie")
    suspend fun getMoviesData(@QueryMap params: HashMap<String, String> ): Response<Movies>

    @GET("/3/search/movie")
    suspend fun searchMovies(@QueryMap params: HashMap<String, String> ): Response<Movies>
}