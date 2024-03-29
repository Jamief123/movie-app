package com.jamieco.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamieco.movieapp.BuildConfig
import com.jamieco.movieapp.data.DetailMovie
import com.jamieco.movieapp.data.MovieRequestAdditions
import com.jamieco.movieapp.network.MovieApi
import kotlinx.coroutines.launch

class DetailViewModel(movieId: Int): ViewModel() {

    private val apiKey = BuildConfig.API_KEY

    private val _detailLiveData = MutableLiveData<DetailMovie>()
    val detailLiveData: LiveData<DetailMovie> = _detailLiveData

    init {
        Log.d("DetailViewModel", "Getting Movie Details for movieId: $movieId")
        getDetails(movieId)
    }

    private fun getDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetails = MovieApi.retrofitService.getMovieDetails(movieId, apiKey, listOf(MovieRequestAdditions.CREDITS))
                val videos = MovieApi.retrofitService.getVideos(movieId, apiKey)
                movieDetails.videos = videos.results
                _detailLiveData.value = movieDetails
            } catch (e: Exception) {
                Log.d("DetailViewModel", "getMovieDetails: $e")
            }
        }
    }
}