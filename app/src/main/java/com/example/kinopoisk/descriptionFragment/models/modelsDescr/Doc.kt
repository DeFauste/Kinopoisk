package com.example.kinopoisk.descriptionFragment.models.modelsDescr

data class Doc(
    val alternativeName: String,
    val countries: List<Country>,
    val description: String,
    val id: Int,
    val movieLength: Int,
    val name: String,
    val poster: Poster,
    val rating: Rating,
    val type: String,
    val videos: Videos,
    val year: Int
)