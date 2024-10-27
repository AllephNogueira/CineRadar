package com.allephnogueira.cineradar.View

import android.content.Intent
import com.allephnogueira.cineradar.ViewModel.FormatarData
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.allephnogueira.cineradar.ViewModel.Adapter
import com.allephnogueira.cineradar.ViewModel.Event
import com.allephnogueira.cineradar.ViewModel.EventResponse
import com.allephnogueira.cineradar.ViewModel.OrdenandoPor
import com.allephnogueira.cineradar.ViewModel.OrdenandoPor.Companion.exibindoCincoUltimosLancamentos
import com.allephnogueira.cineradar.Model.RetrofitClient
import com.allephnogueira.cineradar.R
import com.allephnogueira.cineradar.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemClicado: String

    /** Configuraçao inicial do meu RecyclerView */

    private lateinit var imagemTopo: ImageView
    private lateinit var nomeFilmeTopo: TextView
    private lateinit var dataFilmeTopo: TextView
    private lateinit var generoDoFilme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /** Configuração do binding. */
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
            /**
             * responde.body() é onde chega o corpo do API com os dados
             * passamos para events Se vier os dados, vamos fazer um teste antes
             * let = se o item nao for nulo ele aplica os dados no metodo
             * cardImagemSuperior(it[10]) vamos deixar um filme no card superior
             * iniciarRecyclerView é onde vamos iniciar, passando os dados IT como parametro
             *
             * Antes de passar para o iniciarRecyclerView vamos ordernar a lista por data.
             *
             * Handler vamos repetir de 5 em 5 segundos assim vamos atualizar o card com os ultimos 5 filmes
             * Caso queira saber mais entrar em OdernandoPor, la o metodo (exibindoCincoUltimosLancamentos) esta explicando melhor
             */
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val eventResponse =
                        response.body()
                    val events =
                        eventResponse?.items
                    events?.let {

                        /** Define o Runnable para atualizar a imagem do filme */
                        val handler = Handler(Looper.getMainLooper())
                        val runnable = object : Runnable {
                            override fun run() {
                                cardImagemSuperior(exibindoCincoUltimosLancamentos(it))
                                // Repetir a execução a cada 5 segundos
                                handler.postDelayed(this, 5000)
                            }
                        }
                        handler.post(runnable)/** Inicia a primeira execução */


                        iniciarRecyclerView(OrdenandoPor.ordenandoPorData(it))
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


    private fun viewsParaImagensSuperior() {
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

        val imageUrl = event.images.firstOrNull()?.url
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this).load(imageUrl).into(imagemTopo)
        }

        nomeFilmeTopo.text = event.title

        /** Pegando a data do filme  */
        val dataLocal = event.premiereDate?.localDate ?: "Data não encontrada!"
        val dataFormatada = FormatarData.formatarData(dataLocal)
        dataFilmeTopo.text = dataFormatada.toString()
        /** DATA DO FILME */

        generoDoFilme.text = event.genres.firstOrNull()


        /**
         * Quando o usuario clicar no card la em cima, vamos levar ele para uma outra activity
         * Nessa outra acitivy vamos passar esses dados para ele poder ver a sinopse do filme.
         * */

        imagemTopo.setOnClickListener {
            val intent = Intent(this, DetalhesFilmesActivity::class.java)
            intent.putExtra("EVENT_IMAGE", imageUrl)
            intent.putExtra("EVENT_NOME", event.title)
            intent.putExtra("EVENT_DATA", dataFormatada)
            intent.putExtra("EVENT_GENERO", event.genres.firstOrNull())

            // Verificar sinopse e enviar uma mensagem padrão se não houver
            val filmeSemSinopse = "Desculpe, ainda não temos informação sobre esse filme."
            intent.putExtra("EVENT_SINOPSE", if (event.synopsis.isEmpty()) filmeSemSinopse else event.synopsis)

            startActivity(intent)
        }



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
        binding.recyclerView.adapter = Adapter(events) { events ->
            Toast.makeText(this, events, Toast.LENGTH_SHORT).show()
            itemClicado = events
            Log.d("MainActivity", "ID do item clicado foi $itemClicado")
        }
    }

}

