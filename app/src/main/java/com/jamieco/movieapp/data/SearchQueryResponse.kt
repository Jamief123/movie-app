package com.jamieco.movieapp.data

import com.squareup.moshi.Json

data class SearchQueryResponse(
    @Json(name="page")
    val page: Int,
    @Json(name="results")
    val results: List<Movie>,
    @Json(name="total_pages")
    val totalPages: Int,
    @Json(name="total_results")
    val totalResults: Int
)