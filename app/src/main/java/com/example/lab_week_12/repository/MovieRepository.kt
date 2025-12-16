package com.example.lab_week_12.repository

import com.example.lab_week_12.data.Movie
import com.example.lab_week_12.data.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService
) {

    private val apiKey = "fe0b306c7ed0ad558052346dac7dbc59"

    fun fetchMovies(): Flow<List<Movie>> {

        return flow {
            val response =
                movieService.getPopularMovies(apiKey)

            println("API SUCCESS -> ${response.results.size} movies")

            emit(response.results)

        }.flowOn(Dispatchers.IO)
    }

}
