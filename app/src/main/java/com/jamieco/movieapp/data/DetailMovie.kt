package com.jamieco.movieapp.data

import com.squareup.moshi.Json

data class DetailMovie(

    // Direct JSON mappings
    val overview: String?,
    @Json(name="original_language")
    val originalLanguage: String? = "",
    @Json(name="spoken_language")
    val spokenLanguages: String?,
    @Json(name="original_title")
    val originalTitle: String? = "",
    val video: Boolean,
    val title: String? = "",
    val genreIds: List<Int>?,
    @Json(name="poster_path")
    val posterPath: String? = "",
    @Json(name="backdrop_path")
    val backdropPath: String? = "",
    @Json(name="release_date")
    val releaseDate: String? = "",
    @Json(name="media_type")
    val mediaType: String? = "",
    @Json(name="vote_average")
    val voteAverage: Double? = 0.0,
    val popularity: Double? = 0.0,
    val id: Int? = 0,
    val adult: Boolean? = false,
    @Json(name="vote_count")
    val voteCount: Int? = 0,

    var videos: List<Video>?
)
