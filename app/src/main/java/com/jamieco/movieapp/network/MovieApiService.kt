package com.jamieco.movieapp.network

import com.jamieco.movieapp.data.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("trending/all/day")
    suspend fun trendingMovies(@Query("api_key") apiKey: String): BaseMovieResponse

    @GET("movie/now_playing")
    suspend fun nowPlaying(@Query("api_key") apiKey: String): BaseMovieResponse

    @GET("movie/upcoming")
    suspend fun upcoming(@Query("api_key") apiKey: String): BaseMovieResponse

    @GET("movie/top_rated")
    suspend fun topRated(@Query("api_key") apiKey: String): BaseMovieResponse

    @GET("movie/latest")
    suspend fun latest(@Query("api_key") apiKey: String): Movie

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): DetailMovie

    @GET("movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): BaseVideoResponse
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}