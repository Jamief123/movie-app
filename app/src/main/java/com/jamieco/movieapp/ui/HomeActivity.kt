package com.jamieco.movieapp.ui

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamieco.movieapp.R
import com.jamieco.movieapp.databinding.ActivityHomeBinding
import com.jamieco.movieapp.ui.adapter.CollectionAdapter
import com.jamieco.movieapp.ui.adapter.MovieListAdapter
import com.jamieco.movieapp.viewmodel.HomeViewModel

class HomeActivity: AppCompatActivity(), MovieListAdapter.ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val recyclerView = binding.rvHome
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CollectionAdapter()
        recyclerView.adapter = adapter

        val viewModel = HomeViewModel()
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
}