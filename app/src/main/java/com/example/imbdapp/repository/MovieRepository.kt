package com.example.imbdapp.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.imbdapp.R
import com.example.imbdapp.data.MovieDao
import com.example.imbdapp.data.MovieDataBase
import com.example.imbdapp.models.Movie
import com.example.imbdapp.services.MovieService
import com.example.imbdapp.utilities.Performe
import com.example.imbdapp.utilities.API_KEY_STRING
import com.example.imbdapp.utilities.ParamsEmun
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


class MovieRepository @Inject constructor(val videoDao: MovieDao, val app: Application) {

    val moviesData = MutableLiveData<List<Movie>>()
    val lastPage = MutableLiveData<Int>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = videoDao.getCache()
            if (data.isEmpty()) {
                getPage(1)
            } else {
                moviesData.postValue(data)
                lastPage.postValue(2)
            }
        }
    }

    @WorkerThread
    suspend private fun callWebService(page: Int) {
        if (page > lastPage.value?.toInt() ?: 1) {
            return
        }
        if (netWorkingAvailable()) {
            val params = HashMap<String, String> ()
            params[ParamsEmun.KEY.string] = API_KEY_STRING
            params[ParamsEmun.YEAR.string] = Calendar.getInstance().get(Calendar.YEAR).toString()
            params[ParamsEmun.PAGE.string] = "$page"
            val movies = Performe.request<MovieService>(
                MovieService::class.java)?.getMoviesData(params)?.body()
            movies?.let {
                lastPage.postValue(movies.lastPage)
                if(page > 1){
                    moviesData.value?.toMutableList()?.let {
                        it.addAll(movies.results)
                        moviesData.postValue(it)
                    }
                } else {
                    moviesData.postValue(movies.results)
                    videoDao.deleteCache()
                    videoDao.insertMovies(movies.results)
                }
            }
        } else {
            val data = videoDao.getCache()
            if (!data.isEmpty()) {
                moviesData.postValue(data)
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(app, R.string.Fallback_network,Toast.LENGTH_LONG).show()
            }
        }
    }

    //
    @Suppress("DEPRECATION")
    fun netWorkingAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun getPage(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService(page)
        }
    }
}