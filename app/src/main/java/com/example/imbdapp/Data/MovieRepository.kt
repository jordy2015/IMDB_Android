package com.example.imbdapp.Data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.imbdapp.Utilities.Performe
import com.example.imbdapp.Utilities.API_KEY_STRING
import com.example.imbdapp.Utilities.ParamsEmun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap


class MovieRepository(val app: Application) {

    val moviesData = MutableLiveData<List<Movie>>()
    val lastPage = MutableLiveData<Int>()

    init {
        getPage(1)
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
            val movies = Performe.request<MovieService>(MovieService::class.java)?.getMoviesData(params)?.body()
            movies?.let {
                lastPage.postValue(movies.lastPage)
                if(page > 1){
                    moviesData.value?.toMutableList()?.let {
                        it.addAll(movies.results)
                        moviesData.postValue(it)
                    }
                } else {
                    moviesData.postValue(movies.results)
                }
            }
        }
    }

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