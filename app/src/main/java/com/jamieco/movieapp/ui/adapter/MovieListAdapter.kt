package com.jamieco.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jamieco.movieapp.R
import com.jamieco.movieapp.data.Movie
import com.jamieco.movieapp.databinding.ItemMovieBinding

class MovieListAdapter: ListAdapter<Movie,MovieListAdapter.ItemViewHolder>(ItemCallBack()) {
    class ItemViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.tvTitle.text = movie.originalTitle
        holder.binding.tvRating.text = movie.voteAverage.toString()
        requireNotNull(movie.releaseDate).apply {
            if (this.isNotEmpty()) {
                holder.binding.tvReleaseDate.append(this)
            } else {
                holder.binding.tvReleaseDate.text = holder.binding.root.context.getString(R.string.unreleased)
            }
        }


        Glide.with(holder.binding.root.context)
            .load("https://image.tmdb.org/t/p/w500/"+ getItem(position).posterPath) //TODO remove baseUrl from here
            .into(holder.binding.ivPoster)
    }

    class ItemCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem


        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.originalTitle == newItem.originalTitle
    }
}

