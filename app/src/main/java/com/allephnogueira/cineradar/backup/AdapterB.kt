//package com.allephnogueira.cineradar.backup
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.allephnogueira.cineradar.R
//
//
//// Vamos criar uma classe do tipo RecycleView.Adapter
//// Vamos passar < O nome da classe que criamos em baixo >
//class AdapterB (
//    private val myList: List<String>,   // Aqui recebe minha lista de Eventos
//    val retornoNomeUsuario : (String) -> Unit   // Vamos retornar o nome do Usuario que vai vir do tipo String
//
//
//) : RecyclerView.Adapter<AdapterB.MyViewHolder>() {
//
//
//    // Aqui vamos trabalhar com oque? Nesse exemplo vamos trabalhar com Strings, mas poderiamos trabalhar com objetos também, como os filmes que chegam..
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        // Aqui é responsavel pro criar o Layout de cada linha que nos temos.
//        // Agora aqui vamos criar as linhas de codigo responsavel por modificar cada elemento da tela
//
//        // aqui no inflate temos que passar 3 informaçoes
//        // 1 o xml que criamos
//        // 2 View Group, que podemos passar o parent
//        // Terceiro podemos passar um false
//
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun getItemCount(): Int {
//        // Aqui é onde vamos passar o total de itens que ele tem que criar, exemplo vamos dizer que queremos criar 10 filmes
//
//        return myList.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        // Aqui é o metodo que exibimos as informações (Nome do filme, foto do filme...)
//        // Aqui que vamos passar nossas informações para la.
//
//        val nome = myList[position]
//
//
//        holder.textName.text = nome
//        Log.d("Adapter", "Nome do item: $nome")
//
//        //holder.textName.text = nome
//
//        // Aqui é onde vamos tratar os eventos de click
//        // Vamos pegar o holder
//        // O nome do componente que queremos alterar
//        // Aqui vamos passar o nome que colocamos la como construtor da classe.
//        // E vamos passar o nosso nome mesmo, no caso se ele clicar em alleph, vamos passar o alleph pra ele.
//        // Aqui ele so vai habilitar o evento de click se o usuario clicar em cima do componente de textView no caso (textName)
//        /* holder.textName.setOnClickListener{retornoNomeUsuario(nome)} */
//
//        // Podemos fazer para habilitar o evento quando o usuario clicar em cima de qualquer local do componente
//        holder.itemView.setOnClickListener{retornoNomeUsuario(nome)}
//
//
//    }
//
//
//    // Agora vamos criar a class (class que vamos utilizar la em cima no tipo do Recycle
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
//
//
//        // Aqui vamos colocar os componentes que temos no nosso layout
//        // *** repara que estamos usando a classe itemView que tem os metodos de pegar os dados da view ***
//        val textName : TextView = itemView.findViewById(R.id.textCardFilme)
//        // NOME DO COMPONENTE (textName)
//    }
//}
