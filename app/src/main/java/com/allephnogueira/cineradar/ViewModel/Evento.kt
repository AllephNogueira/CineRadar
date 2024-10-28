package com.allephnogueira.cineradar.ViewModel

data class EventResponse(
    val items: List<Event>
)

data class Event(
    val id: String,
    val title: String,
    val date: String,
    val synopsis: String,
    val inPreSale: Boolean,
    val images: List<Image>,
    val genres: List<String>,
    val premiereDate: PremiereDate
)

data class Image(
    val url: String
)

data class PremiereDate(
    val localDate: String,
    val isToday: Boolean,
    val dayOfWeek: String,
    val dayAndMonth: String,
    val hour: String,
    val year: String
)
