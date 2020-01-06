package com.example.imbdapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.imbdapp.Data.MovieRepository

class HomeViewModel(val app: Application) : AndroidViewModel(app){

    private val dataRepo = MovieRepository(app)
    val moviesData = dataRepo.moviesData

    private val _page = MutableLiveData<Int>().apply {
        value = 1
    }

    var page: MutableLiveData<Int> = _page

    fun refreshMoviesData() {
        page.postValue(1)
        dataRepo.getPage(page.value!!)
    }

    fun nextPage() {
        page.postValue(page.value!! + 1)
        dataRepo.getPage(page.value!!)
    }
}