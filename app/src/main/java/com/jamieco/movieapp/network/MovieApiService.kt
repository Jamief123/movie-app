package com.jamieco.movieapp.network

import com.jamieco.movieapp.data.BaseResponse
import com.jamieco.movieapp.data.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
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
    suspend fun trendingMovies(@Query("api_key") apiKey: String): BaseResponse

    @GET("movie/now_playing")
    suspend fun nowPlaying(@Query("api_key",) apiKey: String): BaseResponse

    @GET("movie/upcoming")
    suspend fun upcoming(@Query("api_key",) apiKey: String): BaseResponse

    @GET("movie/top_rated")
    suspend fun topRated(@Query("api_key",) apiKey: String): BaseResponse

    @GET("movie/latest")
    suspend fun latest(@Query("api_key",) apiKey: String): Movie
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}