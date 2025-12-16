package com.example.lab_week_12

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val poster = findViewById<ImageView>(R.id.imgPoster)
        val title  = findViewById<TextView>(R.id.tvTitle)
        val desc   = findViewById<TextView>(R.id.tvOverview)

        val movieTitle   = intent.getStringExtra("TITLE")
        val posterPath   = intent.getStringExtra("POSTER")
        val overviewText= intent.getStringExtra("OVERVIEW")

        title.text = movieTitle
        desc.text  = overviewText

        poster.load("https://image.tmdb.org/t/p/w500$posterPath")
    }
}
