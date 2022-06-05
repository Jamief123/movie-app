package com.jamieco.movieapp.ui.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamieco.movieapp.data.MovieCollection
import com.jamieco.movieapp.databinding.ItemMovieBinding
import com.jamieco.movieapp.databinding.ItemMovieCollectionBinding

class CollectionAdapter : ListAdapter<MovieCollection,
        CollectionAdapter.ItemViewHolder>(ItemCallBack()) {

    class ItemViewHolder(val binding: ItemMovieCollectionBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemMovieCollectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Keep a reference to the internal adapter for the horizontal recyclerView
        val adapter = MovieListAdapter()
        adapter.setItemClickListener(holder.binding.root.context as MovieListAdapter.ItemClickListener)
        with(holder.binding.rvHomeCollection) {
            this.layoutManager = LinearLayoutManager(holder.binding.root.context,
                RecyclerView.HORIZONTAL,
                false
            )
            this.adapter = adapter

        }
        holder.binding.rvHomeCollection.adapter = adapter
        adapter.submitList(getItem(position).list)
        holder.binding.tvCollectionTitle.text = getItem(position).category
    }
}

class ItemCallBack : DiffUtil.ItemCallback<MovieCollection>() {
    override fun areItemsTheSame(oldItem: MovieCollection, newItem: MovieCollection): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: MovieCollection, newItem: MovieCollection): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}
