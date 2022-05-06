package com.jamieco.movieapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jamieco.movieapp.R
import com.jamieco.movieapp.databinding.ActivityMainBinding
import com.jamieco.movieapp.ui.adapter.MovieListAdapter
import com.jamieco.movieapp.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = binding.rvMovieList
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MovieListAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, recyclerView.layoutDirection))

        val viewModel = HomeViewModel()
        viewModel.status.observe(
            this
        ) {
            adapter.submitList(it)
            binding.tvLoading.visibility = View.GONE
        }
    }
}