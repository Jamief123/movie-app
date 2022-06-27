package com.jamieco.movieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.jamieco.movieapp.R
import com.jamieco.movieapp.databinding.ActivitySearchResultsBinding
import com.jamieco.movieapp.ui.adapter.MovieListAdapter
import com.jamieco.movieapp.viewmodel.SearchResultsViewModel


const val QUERY_KEY = "QUERY_KEY"
class SearchResultsActivity: AppCompatActivity(), MovieListAdapter.ItemClickListener {

    private lateinit var viewModel: SearchResultsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySearchResultsBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_results)
        // TODO: Handle empty query
        val query = requireNotNull(intent?.extras?.getString(QUERY_KEY))
        // Todo: Remove hardcoded vertical bars
        binding.tvTitle.text = " | $query"
        viewModel = SearchResultsViewModel(query)

        val rvSearch = binding.rvSearchResults
        rvSearch.layoutManager = GridLayoutManager(this, 2)

        val adapter = MovieListAdapter()
        adapter.setItemClickListener(this)
        rvSearch.adapter = adapter

        viewModel.searchResultsLiveData.observe(this) {
            // submit to adapter
            adapter.submitList(it.results)
        }
    }

    override fun onClickMovie(movieId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movieId.toString())
        // TODO: Add handling for when an invalid ID is passed in i.e. 0
        this.startActivity(intent)
    }
}