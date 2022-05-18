package com.jamieco.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamieco.movieapp.R
import com.jamieco.movieapp.databinding.HomeActivityBinding
import com.jamieco.movieapp.ui.adapter.CollectionAdapter
import com.jamieco.movieapp.viewmodel.HomeViewModel

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: HomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)
        val recyclerView = binding.rvHome
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CollectionAdapter()
        recyclerView.adapter = adapter

        // Observe viewmodel for data.
        // For now, use fake data from the viewmodel.
        val viewModel = HomeViewModel()
        viewModel.categoriesLiveData.observe(
            this
        ) {
            adapter.submitList(it)
        }
    }
}