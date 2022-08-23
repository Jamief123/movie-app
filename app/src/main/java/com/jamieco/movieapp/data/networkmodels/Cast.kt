package com.jamieco.movieapp.data.networkmodels

import com.squareup.moshi.Json

data class Cast(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int,
    @Json(name="known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    @Json(name="original_name")
    val originalName: String?,
    val popularity: Double?,
    @Json(name="profile_path")
    val profilePath: String?,
    @Json(name="cast_id")
    val castId: Int?,
    val character: String?,
    @Json(name="credit_id")
    val creditId: String?,
    val order: Int?
)
