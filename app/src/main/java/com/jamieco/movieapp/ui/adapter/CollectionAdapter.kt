package com.jamieco.movieapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamieco.movieapp.data.MovieCollection
import com.jamieco.movieapp.databinding.ItemMovieBinding
import com.jamieco.movieapp.databinding.ItemMovieCollectionBinding

class CollectionAdapter : ListAdapter<MovieCollection, CollectionAdapter.ItemViewHolder>(ItemCallBack()) {


    class ItemViewHolder(val binding: ItemMovieCollectionBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ItemCallBack : DiffUtil.ItemCallback<MovieCollection>() {
    override fun areItemsTheSame(oldItem: MovieCollection, newItem: MovieCollection): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: MovieCollection, newItem: MovieCollection): Boolean =
        oldItem.collectionId == newItem.collectionId
}
