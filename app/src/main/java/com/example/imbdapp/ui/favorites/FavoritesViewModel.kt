package com.example.imbdapp.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbdapp.data.MovieDao
import com.example.imbdapp.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(val videoDao: MovieDao) : ViewModel() {
    val moviesData = MutableLiveData<List<Movie>>()

    fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = videoDao.getFavorites()
            if (!data.isEmpty()) {
                moviesData.postValue(data)
            }
        }
    }
}