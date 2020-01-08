package com.example.imbdapp.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbdapp.models.Movie
import com.example.imbdapp.services.MovieService
import com.example.imbdapp.utilities.API_KEY_STRING
import com.example.imbdapp.utilities.ParamsEmun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val service: MovieService) : ViewModel() {

    val moviesData = MutableLiveData<List<Movie>>()

    fun search(queryKey: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val params = HashMap<String,String>()
            params[ParamsEmun.KEY.string] = API_KEY_STRING
            params[ParamsEmun.PAGE.string] = "1"
            params[ParamsEmun.QUERY.string] = queryKey
            service.searchMovies(params).body()?.results?.let {
                moviesData.postValue(it)
            }
        }
    }

}