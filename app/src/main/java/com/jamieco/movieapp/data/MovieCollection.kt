package com.jamieco.movieapp.data

data class MovieCollection(
    val category: String? = "Trending",
    val list: List<Movie>
)
