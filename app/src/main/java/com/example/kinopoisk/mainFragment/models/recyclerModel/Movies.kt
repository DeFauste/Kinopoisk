package com.example.kinopoisk.mainFragment.models.recyclerModel

data class Movies(
    val alternativeName: String,
    val countries: List<Country>,
    val description: String,
    val id: Int,
    val name: String,
    val poster: Poster,
    val premiere: Premiere,
    val rating: Rating,
    val year: Int,
)