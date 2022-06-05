package com.jamieco.movieapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jamieco.movieapp.databinding.ActivityDetailBinding
import com.jamieco.movieapp.R
import com.jamieco.movieapp.data.DetailMovie
import com.jamieco.movieapp.viewmodel.DetailViewModel

const val MOVIE_KEY = "MOVIE_KEY"
private const val TAG = "Detail Activity"

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val movieId = requireNotNull(
            intent?.extras?.getString(MOVIE_KEY)?.toInt()
        )
        Toast.makeText(this, "Movie ID: $movieId", Toast.LENGTH_SHORT).show()
        val viewModel = DetailViewModel(movieId)
        viewModel.detailLiveData.observe(this) {
            bindViews(it)
        }
    }

    private fun bindViews(movie: DetailMovie) {
        binding.tvDetailTitle.text = movie.originalTitle
    }
}