package com.example.lab_week_12.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesResponse(
    val results: List<Movie>
)
