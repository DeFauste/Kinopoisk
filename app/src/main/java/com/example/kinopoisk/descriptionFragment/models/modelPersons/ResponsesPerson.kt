package com.example.kinopoisk.descriptionFragment.models.modelPersons

data class ResponsesPerson(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)