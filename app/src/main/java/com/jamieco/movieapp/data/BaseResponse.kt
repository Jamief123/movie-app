package com.jamieco.movieapp.data

data class BaseResponse(
    val page: Int,
    val results: List<Movie>
)