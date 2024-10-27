package com.allephnogueira.cineradar.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allephnogueira.cineradar.R
import com.allephnogueira.cineradar.Service.Event
import com.bumptech.glide.Glide




/**
 * Classe Adapter é a classe que vamos utilizar para renderizar tudo na nossa tela
 * Ela espera receber objetos do tipo Event
 * Vamos retornar a informação do filme que foi clicado para poder criar uma outra interface passando os dados do filme.
 */
class Adapter (
    private val myList: List<Event>,
    val retornoDadosDoFilme : (String) -> Unit) : RecyclerView.Adapter<Adapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        /**
         * Aqui é responsavel por criar o layout
         * Informações que estão sendo passadas
         * 1 O XML que criamos
         * 2 View Group:
         * 3 Terceiro podemos passar um false
         */

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        /**
         * Aqui vamos pegar o total de elementos que vamos precisar criar
         * No caso aqui ele esta pegando o total da lista.
         */

        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /**
        * Nesse metodo vamos passar os dados para a acitivity, vamos puxar os dados que vem do servidor e passar para ela
        * Vamos passar por exemplo eventos (Onde vem os dados do servidor) . title (Titulo do filme)
         *
        * eventos = onde esta os dados que vamos precisar trabalhar (Titulos, datas, sinopse)
        **/

        val eventos = myList[position]
        Log.d("Adapter", "Nome dos filmes que esta vindo da API: ${eventos.title}")

        /**
         * Aqui estamos trazendo a imagem usando a biblioteca Glide
         *
         */
        Glide.with(holder.itemView.context)
            .load(eventos.images.firstOrNull()?.url)
            .into(holder.imagemCard)
        Log.d("Adapter", "Verificando se a imagem esta chegando ${eventos.images.firstOrNull()}")

        holder.textName.text = eventos.title
        holder.textGenero.text = eventos.genres.firstOrNull()

        /**
         * Formatando a data para exibir.
         */
        val dataLocal = eventos.premiereDate?.localDate ?: "Data não encontrada!"
        val dataFormatada = FormatarData.formatarData(dataLocal)
        holder.textData.text = dataFormatada //
        Log.d("Adapter", "Tipo do retorno da data: $dataFormatada")

        /**
         * Aqui vamos tratar os eventos de click
         * Vamos pegar o click direto na View e exibir (podemos exibir o titulo, data, id...)
         *
         */

        holder.itemView.setOnClickListener{retornoDadosDoFilme(eventos.toString())}


    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {

        /**
        Aqui vamos colocar todos os componentes que vai ser utilizado na minha interface
        imageCard = Variavel que vai receber o componente de imagem no caso a imageFilmeCard
        Agora la em cima que vamos manipular qual tipo de imagem que vai ter dentro dela
        **/


        val imagemCard : ImageView = itemView.findViewById(R.id.imageFilmeCard)
        val textName : TextView = itemView.findViewById(R.id.textCardFilme)
        val textGenero : TextView = itemView.findViewById(R.id.textCardFilmeGenero)
        val textData : TextView = itemView.findViewById(R.id.textCardFilmeData)

    }
}
