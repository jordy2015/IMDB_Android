package com.example.imbdapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imbdapp.models.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movies")
    fun getAll(): List<Movie>

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Insert
    suspend fun insertMovies(movies: List<Movie>)

}