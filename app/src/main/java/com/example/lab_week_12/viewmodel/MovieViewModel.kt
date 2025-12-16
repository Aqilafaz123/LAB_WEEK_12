package com.example.lab_week_12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_12.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovies =
        MutableStateFlow(emptyList<com.example.lab_week_12.data.Movie>())

    val popularMovies: StateFlow<List<com.example.lab_week_12.data.Movie>>
            = _popularMovies

    private val _error =
        MutableStateFlow("")

    val error: StateFlow<String> = _error

    init {
        fetchMovies()
    }

    private fun fetchMovies() {

        viewModelScope.launch(Dispatchers.IO) {

            movieRepository
                .fetchMovies()
                .catch {
                    _error.value =
                        "Error: ${it.message}"
                }
                .collect {

                    // SORTING assignment
                    _popularMovies.value =
                        it.sortedByDescending { movie ->
                            movie.popularity
                        }

                }
        }
    }
}
