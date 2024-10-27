// MovieDetailActivity.kt
package com.allephnogueira.cineradar.View

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.allephnogueira.cineradar.R
import com.bumptech.glide.Glide

class DetalhesFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes_detalhes)

        // Recuperando o ID do evento passado pela Intent
        val imageUrl: String? = intent.getStringExtra("EVENT_IMAGE")
        // Supondo que você tenha um ImageView com o ID imageView
        Glide.with(this)
            .load(imageUrl)
            .into(findViewById<ImageView>(R.id.imageDetalhesFilme))



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

