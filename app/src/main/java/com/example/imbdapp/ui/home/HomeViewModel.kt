package com.example.imbdapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbdapp.models.Movie
import com.example.imbdapp.repository.MovieRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val app: Application) : AndroidViewModel(app) {
    var page: Int = 1
    private val repository = MovieRepository(app)
    val moviesData = repository.moviesData
    val selectedMovie = MutableLiveData<Movie>()

    fun refreshMoviesData() {
        page = 1
        repository.getPage(page)
    }

    fun nextPage() {
        repository.getPage(page)
    }
}