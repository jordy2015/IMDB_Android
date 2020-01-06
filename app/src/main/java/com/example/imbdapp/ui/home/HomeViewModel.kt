package com.example.imbdapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.imbdapp.Repository.MovieRepository

class HomeViewModel(val app: Application) : AndroidViewModel(app){

    private val dataRepo = MovieRepository(app)
    private var page: Int = 1
    val moviesData = dataRepo.moviesData

    fun refreshMoviesData() {
        page = 1
        dataRepo.getPage(page)
    }

    fun nextPage() {
        page++
        dataRepo.getPage(page)
    }
}