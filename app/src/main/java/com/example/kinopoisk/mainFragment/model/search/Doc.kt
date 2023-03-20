package com.example.kinopoisk.mainFragment.model.search

data class Doc(
    val alternativeName: String,
    val countries: List<Country>,
    val description: String,
    val id: Int,
    val name: String,
    val poster: Poster,
    val rating: Rating,
    val year: Int
)