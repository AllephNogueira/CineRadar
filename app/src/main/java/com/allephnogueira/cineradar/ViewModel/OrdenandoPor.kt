package com.allephnogueira.cineradar.ViewModel

import android.util.Log

class OrdenandoPor {
    /**
     * Aqui é uma funçao onde vamos ordenar nossos itens
     */

    companion object {

        var numerosParaContagem = 0

        fun ordenandoPorData(events : List<Event>) : List<Event>{

            /**
             * Aqui vamos pegar nossa lista que vem do servidor e vamos ordena por data.
             */

            val itensFiltrados = events.filter { event ->
                // Retorna verdadeiro se a data não for nula
                event.premiereDate?.localDate != null && event.premiereDate.localDate != "0000-01-01"
            }

            // Agora ordena os itens filtrados por data, usando a data original
            val itensOrdenadosPorData = itensFiltrados.sortedBy { event ->
                // Usa a data original para a ordenação
                event.premiereDate.localDate
            }

            // Log da lista ordenada para ver o resultado
            Log.d(
                "Adapter",
                "Itens com data formatada e ordenada: $itensOrdenadosPorData"
            )


            // Log para verificar a lista ordenada
            Log.d(
                "Adapter",
                "Itens com data formatada e ordenada: $itensOrdenadosPorData"
            )
            // Inicia o RecyclerView com a lista ordenada
            return itensOrdenadosPorData



        }


        fun exibindoCincoUltimosLancamentos(events: List<Event>) : Event {
            val quantidadeDeItensQueVamosExibir = 5

            /**
             * Aqui estamos ordenando os itens pela data
             * OrdenandoPorData() Retorna a lista de events que vem do servidor API
             * Ai pegamos esse retorno e filtramos para exibir apenas 5 elementos
             *
             * Lembrar que: aqui estamos pegando na ordem dos mais novos para o mais antigo do 0 ate 5
             */
            val itensOrdenadosPorData = ordenandoPorData(events)
            val itensLimitados = itensOrdenadosPorData.take(quantidadeDeItensQueVamosExibir)

            /**
             * Fazer uma contagem de 0 ate 5 quando chegar no 5 retornar para o 0
             * Assim vamos repetir os 5 ultimos lancamentos.
             */

            if (numerosParaContagem >= 5) {
                /**
                 * Vamos usar uma funçao la na principal para ficar chamando essa funçao de 5 em 5 segundos
                 * quando chegar no ultimo indice ele retorna para o 0
                 */
                numerosParaContagem = 0
            }
            val numerosAleatorios = numerosParaContagem
            numerosParaContagem++

            Log.d("OrdenandoPor", "Criando um indice aleatorio $numerosAleatorios")
            return itensLimitados[numerosAleatorios]




        }



    }
}