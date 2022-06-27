package com.jamieco.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamieco.movieapp.R
import com.jamieco.movieapp.data.Movie
import com.jamieco.movieapp.data.SearchQueryResponse
import com.jamieco.movieapp.databinding.ActivityHomeBinding
import com.jamieco.movieapp.ui.adapter.CollectionAdapter
import com.jamieco.movieapp.ui.adapter.MovieListAdapter
import com.jamieco.movieapp.viewmodel.HomeViewModel

class HomeActivity: AppCompatActivity(), MovieListAdapter.ItemClickListener {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        supportActionBar?.hide()

        val searchBar = binding.includedToolbar.mainSearchBar
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null || query.isEmpty()) return false
                handleSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText.isEmpty()) return false
                handleQueryTextChange(newText)
                return true
            }
        })

        val recyclerView = binding.rvHome
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CollectionAdapter()
        recyclerView.adapter = adapter

        viewModel = HomeViewModel()
        viewModel.categoriesLiveData.observe(
            this
        ) {
            adapter.submitList(it)
        }
    }

    override fun onClickMovie(movieId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movieId.toString())
        // TODO: Add handling for when an invalid ID is passed in i.e. 0
        this.startActivity(intent)
    }

    private fun handleSubmit(query: String) {
        val intent = Intent(this, SearchResultsActivity::class.java)
        intent.putExtra(QUERY_KEY, query)
        // TODO: Add handling for when an invalid ID is passed in i.e. 0
        this.startActivity(intent)
    }

    private fun handleQueryTextChange(newText: String) {
    }
}