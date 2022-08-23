package com.jamieco.movieapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamieco.movieapp.databinding.ActivityDetailBinding
import com.jamieco.movieapp.R
import com.jamieco.movieapp.data.DetailMovie
import com.jamieco.movieapp.data.ui.detail.Cast
import com.jamieco.movieapp.ui.adapter.CastAdapter
import com.jamieco.movieapp.viewmodel.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.util.*

const val MOVIE_KEY = "MOVIE_KEY"
private const val TAG = "Detail Activity"

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        lifecycle.addObserver(binding.youtubePlayerView)
        val movieId = intent?.extras?.getString(MOVIE_KEY)?.toInt() ?: -1

        val viewModel = DetailViewModel(movieId)
        viewModel.detailLiveData.observe(this) {
            bindViews(it)
        }
    }

    private fun bindViews(movie: DetailMovie) {
        binding.tvDetailTitle.text = movie.originalTitle
        binding.tvOverview.text = movie.overview
        val youtubePlayer = binding.youtubePlayerView
        youtubePlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                binding.youtubePlayerView.visibility = View.VISIBLE
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
        val castAdapter = CastAdapter()
        binding.rvCast.adapter = castAdapter
        binding.rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val castList = movie.credits?.cast
            ?.filter { it.name?.isNotEmpty() == true && it.profilePath?.isNotEmpty() == true }
            ?.map { Cast(it.id, it.profilePath!!, it.name!!) }
            ?.take(10)
        castList?.let { castAdapter.submitList(castList) }
    }
}