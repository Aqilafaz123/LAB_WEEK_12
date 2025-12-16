package com.example.lab_week_12.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lab_week_12.R
import com.example.lab_week_12.data.Movie

class MovieAdapter(
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun addMovies(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val poster: ImageView =
            v.findViewById(R.id.imgPoster)

        val title: TextView =
            v.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val movie = movies[position]

        holder.title.text = movie.title

        holder.poster.load(
            "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        )

        holder.itemView.setOnClickListener {
            onClick(movie)
        }
    }

    override fun getItemCount(): Int =
        movies.size
}
