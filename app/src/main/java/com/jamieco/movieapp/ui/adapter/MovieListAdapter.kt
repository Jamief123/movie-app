package com.jamieco.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        holder.binding.tvTitle.text = getItem(position).originalTitle
        holder.binding.tvRating.text = getItem(position).voteAverage.toString()
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

