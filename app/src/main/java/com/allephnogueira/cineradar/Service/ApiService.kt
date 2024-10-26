package com.allephnogueira.cineradar.Service
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v0/events/coming-soon/partnership/desafio")
    fun getUpcomingEvents(): Call<EventResponse>
}
