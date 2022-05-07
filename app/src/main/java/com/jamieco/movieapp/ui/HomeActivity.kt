package com.jamieco.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jamieco.movieapp.R
import com.jamieco.movieapp.databinding.HomeActivityBinding
import com.jamieco.movieapp.ui.adapter.CollectionAdapter

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: HomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)
        val recyclerView = binding.rvHome

        recyclerView.adapter = CollectionAdapter()
    }
}