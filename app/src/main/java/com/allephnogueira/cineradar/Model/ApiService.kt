package com.allephnogueira.cineradar.Model
import com.allephnogueira.cineradar.ViewModel.EventResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v0/events/coming-soon/partnership/desafio")
    fun getUpcomingEvents(): Call<EventResponse>
}
