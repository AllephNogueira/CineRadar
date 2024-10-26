package com.allephnogueira.cineradar.Service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRetrofit {

    companion object {

        fun pegarDados() {
            val api = RetrofitClient.instance
            api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    if (response.isSuccessful) {
                        val eventResponse = response.body()
                        val events = eventResponse?.items
                        events?.forEach {
                            println("MainActivity  -  Event: ${it}")
                        }
                    } else {
                        println("Erro: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    println("MainActivity -   Falha ao buscar eventos: ${t.message}")
                }
            })

        }

    }


}