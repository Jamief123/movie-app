package com.jamieco.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamieco.movieapp.BuildConfig
import com.jamieco.movieapp.data.SearchQueryResponse
import com.jamieco.movieapp.network.MovieApi
import kotlinx.coroutines.launch

class SearchResultsViewModel(val query: String): ViewModel() {
    private val apiKey = BuildConfig.API_KEY

    private val _searchResultsLiveData = MutableLiveData<SearchQueryResponse>()
    val searchResultsLiveData: LiveData<SearchQueryResponse> = _searchResultsLiveData

    init {
        performSearch(query)
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            try{
                val searchResults = MovieApi.retrofitService.searchQuery(
                    apiKey,
                    query,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                _searchResultsLiveData.postValue(searchResults)
            } catch (e: Exception) {
                Log.d("HomeViewModel", "performSearch: $e")
            }
        }
    }
}
