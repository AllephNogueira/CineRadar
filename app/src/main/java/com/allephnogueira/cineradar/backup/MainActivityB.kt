//package com.allephnogueira.cineradar.backup
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.allephnogueira.cineradar.Adapter.Adapter
//import com.allephnogueira.cineradar.Service.Event
//import com.allephnogueira.cineradar.Service.EventResponse
//import com.allephnogueira.cineradar.Service.RetrofitClient
//import com.allephnogueira.cineradar.databinding.ActivityMainBinding
//import com.bumptech.glide.Glide
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//
//
//
//class MainActivityB : AppCompatActivity() {
//
//
//    //*** configuração inicial
//    private lateinit var binding: ActivityMainBinding
//
//
//    private lateinit var imagemTopo: ImageView
//    private lateinit var nomeFilmeTopo: TextView
//    private lateinit var dataFilmeTopo: TextView
//    private lateinit var generoDoFilme: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater) // **** configuração do binding
//        enableEdgeToEdge()
//        setContentView(binding.root) //***
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        Log.d("MainActivity", "Activity criada")
//
//        initRecycleView()
//
//
//
//        // Inicialize as Views
//        imagemTopo = findViewById(R.id.imageAnuncio)
//        nomeFilmeTopo = findViewById(R.id.textNomeTopo)
//        dataFilmeTopo = findViewById(R.id.textTopoData)
//        generoDoFilme = findViewById(R.id.textTopoGenero)
//        Log.d("MainActivity", "Views inicializadas")
//
//        val api = RetrofitClient.instance
//        api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
//            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
//                if (response.isSuccessful) {
//                    val eventResponse = response.body()
//                    val events = eventResponse?.items
//                    events?.let {
//                        displayEvent(it[15]) // Aqui é one vamos passar o parametro do ultimo lancamento.
//                        if (it.size >= 6) {
//                            // Lista em ordem por data
//                            // Aqui vamos organizar os eventos por data depois.
//                            displayMultipleEvents(it) // aqui é onde vamos passar o parametro dos filmes do encarte a baixo
//                        } else {
//                            Log.e("MainActivity", "Eventos insuficientes para mostrar vários filmes.")
//                        }
//                    }
//                } else {
//                    Log.e("MainActivity", "Erro: ${response.errorBody()?.string()}")
//                }
//            }
//
//            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
//                Log.e("MainActivity", "Falha ao buscar eventos: ${t.message}")
//            }
//        })
//    } // FIM onCreate
//
//    private fun displayEvent(event: Event) {
//        nomeFilmeTopo.text = event.title
//        val imageUrl = event.images.firstOrNull()?.url
//        if (!imageUrl.isNullOrEmpty()) {
//            Glide.with(this).load(imageUrl).into(imagemTopo)
//        }
//        dataFilmeTopo.text = event.date
//        generoDoFilme.text = event.genres.firstOrNull() // Generos vem em lista, vem com mais de um, vamos pegar apenas o primeiro e exibir.
//    }
//
//    private fun displayMultipleEvents(events: List<Event>) {
//        // Presumindo que você tem várias ImageViews para exibir múltiplos filmes
//
////        // Criando varios encartes com os dados do filme
////        for (i in 1..6) {
////            val imagemFilme: ImageView = findViewById(resources.getIdentifier("imageFilmeCard$i", "id", packageName))
////            val nomeFilme: TextView = findViewById(resources.getIdentifier("TextCardFilme$i", "id", packageName))
////            nomeFilme.text = events[i-1].title
////            Glide.with(this).load(events[i-1].images.firstOrNull()?.url).into(imagemFilme)
////        }
//
////        val imagemFilme1: ImageView = findViewById(R.id.imageFilmeCard1)
////        val imagemFilme2: ImageView = findViewById(R.id.imageFilmeCard2)
////        val imagemFilme3: ImageView = findViewById(R.id.imageFilmeCard3)
////        val imagemFilme4: ImageView = findViewById(R.id.imageFilmeCard4)
////        val imagemFilme5: ImageView = findViewById(R.id.imageFilmeCard5)
////        val imagemFilme6: ImageView = findViewById(R.id.imageFilmeCard6)
////        Glide.with(this).load(events[0].images.firstOrNull()?.url).into(imagemFilme1)
////        Glide.with(this).load(events[1].images.firstOrNull()?.url).into(imagemFilme2)
////        Glide.with(this).load(events[2].images.firstOrNull()?.url).into(imagemFilme3)
////        Glide.with(this).load(events[3].images.firstOrNull()?.url).into(imagemFilme4)
////        Glide.with(this).load(events[4].images.firstOrNull()?.url).into(imagemFilme5)
////        Glide.with(this).load(events[5].images.firstOrNull()?.url).into(imagemFilme6)
////        Log.d("MainActivity", "Eventos múltiplos atualizados.")
////         Atualizando também os TextViews se necessário
//    }
//
//
////     Metodo para passar o RecycleView
//
//    private fun initRecycleView() {
//        // layoutManager é a forma da gente exibir o recycleView
//        // Vamos colocar para ele exibir um a baixo do outro.
//        // Também temos o grid que podemos dividir os itens em coluna
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        // Isso aqui é para ele gerar uma performance melhor no carregamento.
//        binding.recyclerView.setHasFixedSize(true)
//        // Aqui ele espera receber uma lista de String que foi oque passamos la.
//        binding.recyclerView.adapter = Adapter(getList()) { nome -> // Isso aqui a gente chama de CallBack é um ouvinte.
//            Toast.makeText(this, nome, Toast.LENGTH_SHORT).show() // Aqui vamos passar a string que recebemos
//        }
//    }
//
//
//    private fun getList() = listOf ("Alleph", "Fernanda", "Crixus", "Amora", "Anastacia", "Calopsita Pai", "Calopsita Mae", "Zeca", "Bethoven"
//        ,"Alleph", "Fernanda", "Crixus", "Amora", "Anastacia", "Calopsita Pai", "Calopsita Mae", "Zeca", "Bethoven")
//
//}
//
