package com.jamieco.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamieco.movieapp.BuildConfig
import com.jamieco.movieapp.data.Movie
import com.jamieco.movieapp.data.MovieCollection
import com.jamieco.movieapp.network.MovieApi
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val apiKey = BuildConfig.API_KEY
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData <List<Movie?>>()

    // The external immutable LiveData for the request status
    val status: LiveData<List<Movie?>> = _status

    private val _categoriesLiveData = MutableLiveData<List<MovieCollection>>()
    val categoriesLiveData: LiveData<List<MovieCollection>> = _categoriesLiveData

    init {
        // getTrendingMovies()
        Log.d("HomeViewModel", "getCategoriesData: ")
        getCollections()
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

    private fun getCollections() {
        // Reuse same API endpoint for now, and duplicate data.
        viewModelScope.launch {
            try {

                val trendingCollection = MovieApi.retrofitService.trendingMovies(apiKey)
                    .results.filter {  it.originalTitle != null && it.originalTitle.isNotEmpty() }
                val nowPlayingCollection = MovieApi.retrofitService.nowPlaying(apiKey)
                    .results.filter {  it.originalTitle != null && it.originalTitle.isNotEmpty() }
                val upcomingCollection = MovieApi.retrofitService.upcoming(apiKey)
                    .results.filter {  it.originalTitle != null && it.originalTitle.isNotEmpty() }
                val topRatedCollection = MovieApi.retrofitService.topRated(apiKey)
                    .results.filter {  it.originalTitle != null && it.originalTitle.isNotEmpty() }
//                val latestCollection = MovieApi.retrofitService.latest(apiKey)

                val collectionList = listOf(
                    MovieCollection("Trending", trendingCollection),
                    MovieCollection("Now Playing", nowPlayingCollection),
                    MovieCollection("Upcoming", upcomingCollection),
                    MovieCollection("Top Rated", (topRatedCollection))
                )
                _categoriesLiveData.value = collectionList
            } catch (e: Exception) {
                Log.d("HomeViewModel", "getCategoriesData: $e")
            }
        }
    }
}