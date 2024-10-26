package com.allephnogueira.cineradar.Service

data class EventResponse(
    val items: List<Event>
)

data class Event(
    val id: String,
    val title: String,
    val date: String,
    val images: List<Image>,
    val genres: List<String>
)

data class Image(
    val url: String
)
