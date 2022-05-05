package com.jamieco.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamieco.movieapp.BuildConfig
import com.jamieco.movieapp.data.Movie
import com.jamieco.movieapp.network.MovieApi
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val apiKey = BuildConfig.API_KEY
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData <List<Movie?>>()

    // The external immutable LiveData for the request status
    val status: LiveData<List<Movie?>> = _status

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                val listResult = MovieApi.retrofitService.trendingMovies(apiKey)
                Log.d("TAG", "getTrendingMovies: ${listResult.results[0].originalTitle}")
                _status.value = listResult.results.filter { it.originalTitle!!.isNotEmpty() }
            } catch (e: Exception) {
                Log.d("HomeViewModel", "getTrendingMovies: $e")
            }
        }
    }
}