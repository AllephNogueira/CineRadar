// MovieDetailActivity.kt
package com.allephnogueira.cineradar

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Recuperando o ID do evento passado pela Intent
        val imageUrl: String? = intent.getStringExtra("EVENT_IMAGE")
        // Supondo que você tenha um ImageView com o ID imageView
        Glide.with(this)
            .load(imageUrl)
            .into(findViewById<ImageView>(R.id.imageDetalhesFilme)) // Substitua `imageView` pelo ID real do seu ImageView na layout



        val nomeFilme : TextView = findViewById(R.id.textDetalhesNomeFilme)
        nomeFilme.text = intent.getStringExtra("EVENT_NOME")

        val generoFilme : TextView = findViewById(R.id.textDetalhesGenero)
        generoFilme.text = intent.getStringExtra("EVENT_GENERO")

        val dataFilme : TextView = findViewById(R.id.textDetalhesData)
        dataFilme.text = intent.getStringExtra("EVENT_DATA")

        val sinopseFilme : TextView = findViewById(R.id.textDetalhesSinopse)
        sinopseFilme.text = intent.getStringExtra("EVENT_SINOPSE")

    }
}
//
//    private lateinit var movieImage: ImageView
//    private lateinit var movieTitle: TextView
//    private lateinit var movieGenre: TextView
//    private lateinit var movieReleaseDate: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_movie_detail)
//
//        movieImage = findViewById(R.id.movieImage)
//        movieTitle = findViewById(R.id.movieTitle)
//        movieGenre = findViewById(R.id.movieGenre)
//        movieReleaseDate = findViewById(R.id.movieReleaseDate)
//
//        // Receber dados da Intent
//        val movieId = intent.getStringExtra("MOVIE_ID")
//        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
//        val movieGenre = intent.getStringExtra("MOVIE_GENRE")
//        val movieReleaseDate = intent.getStringExtra("MOVIE_RELEASE_DATE")
//        val imageUrl = intent.getStringExtra("MOVIE_IMAGE_URL")
//
//        // Exibir os dados na UI
//        this.movieTitle.text = movieTitle
//        this.movieGenre.text = movieGenre
//        this.movieReleaseDate.text = movieReleaseDate
//
//        // Carregar a imagem usando Glide
//        Glide.with(this).load(imageUrl).into(movieImage)
//    }
//}
