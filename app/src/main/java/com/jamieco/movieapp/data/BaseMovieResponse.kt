package com.jamieco.movieapp.data

data class BaseMovieResponse(
    val page: Int,
    val results: List<Movie>
)