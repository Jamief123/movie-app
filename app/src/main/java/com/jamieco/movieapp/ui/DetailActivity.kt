package com.jamieco.movieapp.ui

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jamieco.movieapp.databinding.ActivityDetailBinding
import com.jamieco.movieapp.R
import com.jamieco.movieapp.data.DetailMovie
import com.jamieco.movieapp.viewmodel.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import java.util.*

const val MOVIE_KEY = "MOVIE_KEY"
private const val TAG = "Detail Activity"

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        lifecycle.addObserver(binding.youtubePlayerView)
        val movieId = requireNotNull(
            intent?.extras?.getString(MOVIE_KEY)?.toInt()
        )
        val viewModel = DetailViewModel(movieId)
        viewModel.detailLiveData.observe(this) {
            bindViews(it)
        }
    }

    private fun bindViews(movie: DetailMovie) {
        binding.tvDetailTitle.text = movie.originalTitle
        val youtubePlayer = binding.youtubePlayerView
        youtubePlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val video = movie.videos?.filter {
                    it.site.lowercase(Locale.getDefault()).contains("youtube")
                }?.get(0)
                if (video != null) {
                    youTubePlayer.loadVideo(video.key, 0F)
                } else {
                    binding.youtubePlayerView.visibility = View.GONE
                }
            }
        })
    }
}