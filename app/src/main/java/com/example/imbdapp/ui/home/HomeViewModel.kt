package com.example.imbdapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbdapp.models.Movie
import com.example.imbdapp.repository.MovieRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private var page: Int = 1
    val moviesData = repository.moviesData
    val selectedMovie = MutableLiveData<Movie>()

    fun refreshMoviesData() {
        page = 1
        repository.getPage(page)
    }

    fun nextPage() {
        page++
        repository.getPage(page)
    }
}