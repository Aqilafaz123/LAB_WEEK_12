package com.example.lab_week_12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_12.adapter.MovieAdapter
import com.example.lab_week_12.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter { movie ->

            val i =
                Intent(this, DetailActivity::class.java)

            i.putExtra("TITLE", movie.title)
            i.putExtra("POSTER", movie.posterPath)
            i.putExtra("OVERVIEW", movie.overview)

            startActivity(i)
        }

        val recyclerView =
            findViewById<RecyclerView>(R.id.movie_list)

        recyclerView.layoutManager =
            GridLayoutManager(this, 2)

        recyclerView.adapter = movieAdapter

        val repository =
            (application as MovieApplication)
                .movieRepository

        val viewModel =
            ViewModelProvider(
                this,
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel>
                            create(modelClass: Class<T>): T {

                        return MovieViewModel(repository) as T
                    }
                }
            )[MovieViewModel::class.java]

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.popularMovies.collect {
                        movieAdapter.addMovies(it)
                    }
                }

                launch {
                    viewModel.error.collect {
                        if (it.isNotEmpty()) {
                            Snackbar.make(
                                recyclerView,
                                it,
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}
