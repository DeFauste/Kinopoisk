package com.example.kinopoisk.descriptionFragment.models.modelForDescription

data class Rating(
    val await: Int,
    val filmCritics: Double,
    val imdb: Double,
    val kp: Double,
    val russianFilmCritics: Int
)