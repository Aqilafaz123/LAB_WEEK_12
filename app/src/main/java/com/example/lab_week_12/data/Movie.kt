package com.example.lab_week_12.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(

    val id: Int,
    val title: String,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String?
)
