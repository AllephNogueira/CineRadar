package com.allephnogueira.cineradar

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.allephnogueira.cineradar.Adapter.Adapter
import com.allephnogueira.cineradar.Service.Event
import com.allephnogueira.cineradar.Service.EventResponse
import com.allephnogueira.cineradar.Service.RetrofitClient
import com.allephnogueira.cineradar.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding /** Configuraçao inicial do meu RecyclerView */

    private lateinit var imagemTopo: ImageView
    private lateinit var nomeFilmeTopo: TextView
    private lateinit var dataFilmeTopo: TextView
    private lateinit var generoDoFilme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // **** configuração do binding
        enableEdgeToEdge()
        setContentView(binding.root) //***
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("MainActivity", "Activity criada")


        viewsParaImagensSuperior()
        Log.d("MainActivity", "Views inicializadas")

        val api = RetrofitClient.instance
        api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {

                    val eventResponse =
                        response.body() // EventResponse é onde tem os itens que volta do servidor
                    val events =
                        eventResponse?.items // Passamos para dentro de Events os itens, que pode retornar um nulo
                    events?.let { // LET = aplica essa funçao apenas se o operador events nao for nulo
                        cardImagemSuperior(it[10]) // aqui é onde vamos passar a imagem do topo.
                        // IT = valor do evento.
                        iniciarRecyclerView(it) // Passa a lista de eventos para o recyclerView
                    }
                } else {
                    Log.e("MainActivity", "Erro: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.e("MainActivity", "Falha ao buscar eventos: ${t.message}")
            }
        })
    }





    private fun viewsParaImagensSuperior(){
        /**
         * Aqui vamos passar os dados para a imagem de tela superior.
         *
         */
        imagemTopo = findViewById(R.id.imageAnuncio)
        nomeFilmeTopo = findViewById(R.id.textNomeTopo)
        dataFilmeTopo = findViewById(R.id.textTopoData)
        generoDoFilme = findViewById(R.id.textTopoGenero)
    }


    private fun cardImagemSuperior(event: Event) {
        /**
         * Aqui vamos exibir um filme na nossa tela superior
         * E vamos tambem adicionar as views
         */
        nomeFilmeTopo.text = event.title
        val imageUrl = event.images.firstOrNull()?.url
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this).load(imageUrl).into(imagemTopo)
        }
        dataFilmeTopo.text = event.date
        generoDoFilme.text =
            event.genres.firstOrNull()
    }


    private fun iniciarRecyclerView(events: List<Event>) {
        /**
         * Aqui é onde vamos passar os dados la para dentro do Adapter
         * vamos informatar o tipo de dados que vai entrar que é a Lista de Eventos
         * Vamos colocar ele para ser exibido cada filme um em baixo do outro
         * Podemos colocar também de outro formato para exibir um ao lado do outro, alterando a forma
         *      binding.recyclerView.layoutManager = LinearLayoutManager(this)
         *
         * Aqui serve para gerar uma performance melhor no carregamento
         *      binding.recyclerView.setHasFixedSize(true)
         *
         *Aqui ele espera receber os eventos que vem do servidor
         *      binding.recyclerView.adapter = Adapter(events)
         *
         * Apos vamos criar uma lambda para retornar o nome do filme que foi clicado
         *      { events ->  Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
         */


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = Adapter(events) { events -> Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
            }
    }


}

