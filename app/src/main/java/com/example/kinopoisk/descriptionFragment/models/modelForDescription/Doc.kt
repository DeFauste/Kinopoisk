package com.example.kinopoisk.descriptionFragment.models.modelForDescription

data class Doc(
    val alternativeName: String,
    val countries: List<Country>,
    val description: String,
    val enName: Any,
    val externalId: ExternalId,
    val genres: List<Genre>,
    val id: Int,
    val movieLength: Int,
    val name: String,
    val names: List<Name>,
    val poster: Poster,
    val rating: Rating,
    val releaseYears: List<Any>,
    val shortDescription: String,
    val type: String,
    val votes: Votes,
    val watchability: Watchability,
    val year: Int
)