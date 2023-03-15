package com.example.kinopoisk.descriptionFragment.models.modelPersons

data class ResponsesPersons(
    val docPeople: List<DocPerson>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)