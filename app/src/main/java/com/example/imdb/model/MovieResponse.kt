package com.example.imdb.model

data class MovieResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)
