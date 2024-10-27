package com.allephnogueira.cineradar.View

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.cineradar.R
import com.bumptech.glide.Glide

class SplashAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_acitivity)

        // Adicionando log de depuração para indicar o início do onCreate
        Log.d("Debug", "onCreate iniciado")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mudando a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.barraStatus)
        Log.d("Debug", "Cor da barra de status alterada")


        // Carregar gif utilizando Glide.
        // Utilizando explicidamente o find para pegar a imagem.
        Glide.with(this).asGif().load(R.drawable.splash).into(findViewById(R.id.splashImageView))
        Log.d("Debug", "Gif carregado com Glide")


        val tempoParaCarregar: Long = 3000 // Indicando que é 5 segundos.

        // Apos o delay chamar outra activity
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            // Abertura para nova classe
            Log.d("Debug", "iniciando a contagem do tempo $tempoParaCarregar")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, tempoParaCarregar) // Tempo para entrada


    }
}




