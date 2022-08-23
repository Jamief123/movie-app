package com.jamieco.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jamieco.movieapp.data.ui.detail.Cast
import com.jamieco.movieapp.databinding.ItemCastBinding
import com.jamieco.movieapp.databinding.ItemMovieBinding

class CastAdapter: androidx.recyclerview.widget.ListAdapter<Cast, CastAdapter.ItemViewHolder>(ItemCallBack()) {

    class ItemViewHolder(val binding: ItemCastBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cast = getItem(position)

        holder.binding.tvCastName.text = cast.name

        // TODO remove baseUrl from here
        // Use Configuration API to get sizes
        // https://developers.themoviedb.org/3/configuration/get-api-configuration
        Glide.with(holder.binding.root.context)
            .load("https://image.tmdb.org/t/p/w92/${cast.profilePath}")
            .into(holder.binding.ivCastHeadshot)
    }

    class ItemCallBack : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean =
            oldItem == newItem


        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean =
            oldItem.id == newItem.id
    }
}