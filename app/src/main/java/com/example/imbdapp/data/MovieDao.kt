package com.example.imbdapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imbdapp.models.Movie
import com.example.imbdapp.models.Movies

@Dao
interface MovieDao {
    @Query("SELECT * from movies where isFavorite = 0 and watchLater = 0")
    fun getCache(): List<Movie>

    @Query("DELETE from movies where isFavorite = 0 and watchLater = 0")
    suspend fun deleteCache()

    @Query("SELECT * from movies where isFavorite = 1")
    fun getFavorites(): List<Movie>

    @Query("SELECT * from movies where watchLater = 1")
    fun getWatchLeterList(): List<Movie>

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Insert
    suspend fun insertMovies(movies: List<Movie>)
}