package com.example.imbdapp.ui.watchlater

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbdapp.data.MovieDao
import com.example.imbdapp.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WatchLaterViewModel @Inject constructor(val videoDao: MovieDao) : ViewModel() {
    val moviesData = MutableLiveData<List<Movie>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = videoDao.getWatchLeterList()
            if (!data.isEmpty()) {
                moviesData.postValue(data)
            }
        }
    }
}