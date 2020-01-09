package com.example.imbdapp.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbdapp.models.Movie
import com.example.imbdapp.repository.MovieRepository
import javax.inject.Inject

class MoviesListViewModel  @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    var page = MutableLiveData<Int>(1)
    val moviesData = repository.moviesData
    val selectedMovie = MutableLiveData<Movie>()

    fun refreshMoviesData() {
        page.value = 1
        repository.getPage(page.value!!)
    }

    fun nextPage() {
        repository.getPage(page.value!!)
    }
}